/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JDesktopPane;

/**
 *
 * @author lanister
 */
public class Desktop extends JDesktopPane {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(229, 231, 235));
        g.fillRect(0, 0, getWidth(), getHeight());
    }

}
