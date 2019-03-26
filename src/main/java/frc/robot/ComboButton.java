package frc.robot;

import edu.wpi.first.wpilibj.buttons.Button;

public class ComboButton extends Button{

    Button[] buttons;

    public ComboButton(Button... buttons){
        super();
        this.buttons = buttons;
    }

    @Override
    public boolean get() {
        boolean isPressed = true;

        for(Button b : buttons){
            if(!b.get()){
                isPressed = false;
                break;
            }
        }
        return isPressed;
    }
}