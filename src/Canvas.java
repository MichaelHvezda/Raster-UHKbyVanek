




import rasterdata.*;
import org.jetbrains.annotations.NotNull;
import transforms.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu
 *
 * @author PGRF FIM UHK
 * @version 2017
 */

/**
 * Default mod is Waiting for Mod
 * Line - active by press L, draw point by press button on mouse and after that draw line
 * Dragged - active by press K, click on point and by moving mouse you making a line
 * Poli - active by press J, complite poligom made in Line mod, after that change mod on Line again
 * Circle - active by press H, click on point and by moving mouse you making circle and after that you may deside a sector of circle
 * Poligom - active by press P, draw line and poligom from start
 * Seed - active by press O, active seed algorithm
 */


public class Canvas {

	private final JFrame frame;
	private final JPanel panel;
	private final BufferedImage img;
	private @NotNull Raster<Color> raster;
	private @NotNull Raster<Color> pomraster;
	private final @NotNull Presenter<Graphics, Color> presenter;
	private Color cervena = new Color(255,0,0);
	private Color bila = new Color(255,255,255);
	private Color black = new Color(0,0,0);


	public Canvas(final int width, final int height) {
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);



/*
		raster = new FastRaster<>(img,
				color -> color.getRGB(), 	      //Color::getRGB,
				intColor -> new Color(intColor)); //Color::new);
		lineMaker = new HeWhoDrawsLinesNaively<>();
		presenter = new FastPresenter<>();
		lineMader = new HeWhoDrawsLinesRightly<>();
		/*/

		raster = new VavrRaster<>(width,height,black);
		presenter = new SlowPresenter<>(Color::getRGB);
		pomraster=raster;

//*/
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				present(g);
			}
		};

		panel.setPreferredSize(new Dimension(width, height));

		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		panel.requestFocus();
		panel.requestFocusInWindow();


		draw();


	}

	public void clear() {
		Graphics gr = img.getGraphics();
		gr.setColor(black);
		gr.fillRect(0, 0, img.getWidth(), img.getHeight());
	}

	public void present(final Graphics graphics) {

		presenter.present(graphics,raster);
	}

	public void draw() {
		clear();
		raster = raster.withValue(10,10,bila);

	}

	public void start() {
		draw();
		panel.repaint();

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Canvas(800,800)::start);

	}



}//*/