
boolean abiertos[19];

void setup()
{
  Serial.begin(9600);
  
  for(int i=2; i<=9; i++){
    pinMode(i, OUTPUT);
    digitalWrite(i, HIGH);
  }
  
  for(int i=11; i<=19; i++){
    pinMode(i, INPUT);
    (digitalRead(i))?abiertos[i]=false:abiertos[i]=true;
  }
  
//  Serial.println("");
  
}

void loop()
{
  if (Serial.available() > 0) {
    
     int pin = Serial.parseInt();
     
     if(pin > 1){
       digitalWrite(pin, LOW);
       delay(5000);
       digitalWrite(pin, HIGH);
     }
     
  }
  
  delay(100);
  
  for(int i=11; i<=19; i++){
    if(!digitalRead(i) && !abiertos[i]){
      Serial.println(String(i));
      abiertos[i] = true;
      delay(100);
    }else{
      if(digitalRead(i) && abiertos[i]){
        abiertos[i] = false;
      }
    }
  }
  
}
