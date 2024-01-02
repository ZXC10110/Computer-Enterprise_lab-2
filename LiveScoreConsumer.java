import javax.jms.*;

public class LiveScoreConsumer {

    public static void main(String[] args) throws Exception {
        // เชื่อมต่อกับ JMS
        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = factory.createConnection();
        connection.start();

        // สร้าง Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // สร้าง Queue
        Queue queue = session.createQueue("live-score");

        // สร้าง Consumer
        MessageConsumer consumer = session.createConsumer(queue);

        // รอรับข้อความ
        while (true) {
            Message message = consumer.receive();

            // ตรวจสอบประเภทของข้อความ
            if (message instanceof TextMessage) {
                // แปลงข้อความเป็นสตริง
                TextMessage textMessage = (TextMessage) message;
                String liveScore = textMessage.getText();

                // แสดงผลข้อความ
                System.out.println("updated!: " + liveScore);
            }
        }

        // ปิดการเชื่อมต่อ
        connection.close();
    }
}
