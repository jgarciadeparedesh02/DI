import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatSimple {
    public static void main(String[] args) {
        new Thread(() -> createClient(12345, 12346)).start();
        new Thread(() -> createClient(12346, 12345)).start();
    }

    static void createClient(int serverPort, int clientPort) {
        JFrame frame = new JFrame("Chat Simple");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JTextField messageField = new JTextField();
        frame.add(messageField, BorderLayout.SOUTH);

        JButton sendButton = new JButton("Enviar");
        frame.add(sendButton, BorderLayout.EAST);
        frame.setVisible(true);

        // Iniciar el servidor de chat en segundo plano
        ChatServer server = new ChatServer(chatArea, serverPort);
        Thread serverThread = new Thread(server);
        serverThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ChatClient client = new ChatClient(chatArea, messageField, clientPort);
        sendButton.addActionListener(e -> {
            String message = messageField.getText();
            if (!message.isEmpty()) {
                client.sendMessage(message);
                messageField.setText("");
            }
        });
    }
}

class ChatServer implements Runnable {
    private JTextArea chatArea;
    private int port;
    public ChatServer(JTextArea chatArea, int port) {
        this.chatArea = chatArea;
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            chatArea.append("Servidor de chat iniciado en el puerto " + port + "\n");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ChatHandler handler = new ChatHandler(clientSocket, chatArea);
                Thread clientThread = new Thread(handler);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ChatHandler implements Runnable {
    private Socket clientSocket;
    private JTextArea chatArea;

    public ChatHandler(Socket clientSocket, JTextArea chatArea) {
        this.clientSocket = clientSocket;
        this.chatArea = chatArea;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;
            while ((message = reader.readLine()) != null) {
                chatArea.append("Otro: " + message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ChatClient {
    private JTextArea chatArea;
    private JTextField messageField;
    private PrintWriter writer;

    public ChatClient(JTextArea chatArea, JTextField messageField, int clientPort) {
        this.chatArea = chatArea;
        this.messageField = messageField;

        try {
            Socket socket = new Socket("localhost", clientPort);
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        writer.println(message);
        chatArea.append("Tú: " + message + "\n");
    }
}
