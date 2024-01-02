import javax.jms.*;

public class LiveScoreProvider {

    public static void main(String[] args) throws Exception {
        // เชื่อมต่อกับ JMS
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.start();

        // สร้าง Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // สร้าง Queue
        Queue queue = session.createQueue("live-score");

        // สร้าง Producer
        MessageProducer producer = session.createProducer(queue);

        // สร้างข้อความ
        TextMessage message = session.createTextMessage();

        // รับค่าจากผู้ใช้
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter live score: ");
        String liveScore = scanner.nextLine();

        // ตั้งค่าข้อความ
        message.setText(liveScore);

        // ส่งข้อความ
        producer.send(message);

        // ปิดการเชื่อมต่อ
        connection.close();
    }
}
