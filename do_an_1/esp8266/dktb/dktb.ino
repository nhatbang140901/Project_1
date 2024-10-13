#include <ESP8266WiFi.h>                                                    // esp8266 library
#include <FirebaseESP8266.h>                                                // firebase library
#include <DHT.h>                                                            // dht11 temperature and humidity sensor library
#define FIREBASE_HOST "https://appdktb-default-rtdb.firebaseio.com/"                          // the project name address from firebase id
#define FIREBASE_AUTH "yxiROHZRTNazXhmZEzgpRt3rQ4bxxmZx5gtgRmqa"            // the secret key generated from firebase

#define WIFI_SSID "K cho đâu"                                             // input your home or public wifi name 
#define WIFI_PASSWORD "khongbiet@"                                    //password of wifi ssid
 
#define DHTPIN D4  //chân gpio 2 = D4                                                         // what digital pin we're connected to
#define DHTTYPE DHT11                                                       // select dht type as DHT 11 or DHT22


DHT dht(DHTPIN, DHTTYPE);
FirebaseData firebaseData;
int Den_liv = D0;
int Den_bed = D2;
int Den_kit = D3;
float h,t;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay (500);
    Serial.print(".");

    
    }
    dht.begin();
    Serial.println("");
    Serial.println("connected");
    Serial.println(WiFi.localIP());
    Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
    pinMode(Den_liv,OUTPUT);
    pinMode(Den_bed,OUTPUT);
    pinMode(Den_kit,OUTPUT);
    
}

void loop() 
{
    DocCamBien();
    PhongLiv();
    PhongBed();
    PhongKit();
}

void DocCamBien()
{
  h = dht.readHumidity();
  t = dht.readTemperature();

  if (isnan(h) || isnan(t))
  {
    Serial.println("Failed to read from DHT sensor!");
    return;
  }

    Serial.print("Nhiet do: ");
    Serial.print(t);
    Serial.print("*C ");
    Serial.print("Do am: ");
    Serial.print(h);
    Serial.println("% ");
    Firebase.setFloat (firebaseData ,"/DHT11/Nhiet do", t);
    Firebase.setFloat (firebaseData ,"/DHT11/Do am", h);
    delay(200);
}

void PhongLiv()
{
   if(Firebase.get(firebaseData,"/Living_Room/Lamp"))
  {
    if(firebaseData.dataType()=="string")
    {
      String den_liv = firebaseData.stringData();
      if(den_liv=="ON")
      {
        Serial.println("den mo");
        digitalWrite(Den_liv,HIGH);
      }
      else if (den_liv=="OFF")
      {
        Serial.println("den tat");
        digitalWrite(Den_liv,LOW);
      }     
      
    }
  }
}

void PhongKit()
{
  if(Firebase.get(firebaseData,"/Kitchen_Room/Light"))
  {
    if(firebaseData.dataType()=="string")
    {
      String den_kit = firebaseData.stringData();
      if(den_kit=="ON")
      {
        Serial.println("den mo");
        digitalWrite(Den_kit,HIGH);
      }
      else if (den_kit=="OFF")
      {
        Serial.println("den tat");
        digitalWrite(Den_kit,LOW);
      }     
      
    }
  }

}

void PhongBed()

  {
  if(Firebase.get(firebaseData,"/Bed_Room/Lamp"))
  {
    if(firebaseData.dataType()=="string")
    {
      String den_bed = firebaseData.stringData();
      if(den_bed=="ON")
      {
        Serial.println("den mo");
        digitalWrite(Den_bed,HIGH);
      }
      else if (den_bed=="OFF")
      {
        Serial.println("den tat");
        digitalWrite(Den_bed,LOW);
      }     
      
    }
  }

}
