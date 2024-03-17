package frc.robot.utils;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicReference;

import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEvent;

public class RotationSupplier {
   final DoubleSubscriber rotationSub;
   final AtomicReference<Double> rotationValue = new AtomicReference<Double>();

   int valueListenerHandler;
 
   public RotationSupplier() {
     NetworkTableInstance inst = NetworkTableInstance.getDefault();
     NetworkTable table = inst.getTable("PIDRotations");

     rotationSub = table.getDoubleTopic("PIDRotation").subscribe(0.0);
 
     valueListenerHandler = inst.addListener(
         rotationSub,
         EnumSet.of(NetworkTableEvent.Kind.kValueAll),
         event -> {
           rotationValue.set(event.valueData.value.getDouble());
         });
   }
 
   public void periodic() {
     Double value = rotationValue.getAndSet(0.0);
     if (value != 0.0) {
       System.out.println("got new value " + value);
     }
   }
   
   public Double getValue(){
    return rotationValue.get();
   }
}