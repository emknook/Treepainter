package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Guy;

public class GuyPainter {

	private BufferedImage guyImage;
	private int x = 0;
	private int y = 0;
	private final int w = 25;
	private final int h = 37;

	public GuyPainter() {
		try {
			guyImage = ImageIO.read(GuyPainter.class.getResource("/pics/guyposes.png"));
		} catch (IOException e) {
			System.err.println("Image not found");
		}
	}

	public void paintGuy(Guy guy, Graphics g, PaintingPanel p) {
		if (guy != null) {
			switch (guy.getPose()) {
			case Up:
				x = 0;
				y = h;
				break;
			case UpLeft:
				x = w;
				y = h;
				break;
			case UpRight:
				x = w * 3;
				y = h;
				break;
			case DownLeft:
				x = w;
				y = 0;
				break;
			case DownRight:
				x = w * 3;
				y = 0;
				break;
			case Down:
				x = 0;
				y = 0;
				break;
			case Right:
				x = w;
				y = h * 3;
				break;
			case RightStep:
				x = 0;
				y = h * 3;
				break;
			case Left:
				x = 0;
				y = h * 2;
				break;
			case LeftStep:
				x = w;
				y = h * 2;
				break;
			default:
				break;
			}
			g.drawImage(guyImage.getSubimage(x, y, w, h), (int) (guy.getRelX() / 100 * p.getWidth()) - w / 2,
					(int) (guy.getRelY() / 100 * p.getHeight()) - h, null);
		}
	}

}
