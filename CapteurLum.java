import java.util.Random;
import org.eclipse.paho.client.mqttv3.*;



class MQTTCapteurLumiere
{
    MqttClient client;
    MQTTCapteurLumiere() throws MqttException {

        client = new MqttClient("tcp://localhost:1883","capteur_lumiere");
        client.connect();
    }

    public void publish(int temperature) throws MqttException
    {
        int qos=0;
        String content = String.valueOf(temperature);
        String topic = "Puissance_lumiere";

        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        System.out.println("Message publié : " + message);
        client.publish(topic, message);
    }

    public void disconnect() throws MqttException
    {
        client.disconnect();
    }
}

public class CapteurLum {

    public static void main(String[] args) throws InterruptedException, MqttException
    {
        int c=0;
        Random rand = new Random();
        MQTTCapteurLumiere s=new MQTTCapteurLumiere();

        while(c<10000)
        {
            Thread.sleep(5000);
            c++;

            int lumiere = rand.nextInt(101);
            s.publish(lumiere);
        }
        s.disconnect();

    }

}