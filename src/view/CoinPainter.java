package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Coin;

public class CoinPainter {

	private BufferedImage coinImage;
	private int x = 0;
	private int y = 0;

	public CoinPainter() {
		try {
			coinImage = ImageIO.read(CoinPainter.class.getResource("/pics/coinshapes.png"));
		} catch (IOException e) {
			System.err.println("Image not found");
		}
	}

	public void paintCoin(Coin coin, Graphics g, PaintingPanel p) {
		if (coin != null) {
			g.drawImage(
					coinImage.getSubimage((x + coin.getSize() * (int) coin.getShape()), y, coin.getSize(),
							coin.getSize()),
					(int) (coin.getRelX() / 100 * p.getWidth()) - coin.getSize() / 2,
					(int) (coin.getRelY() / 100 * p.getHeight()) - coin.getSize(), null);
		}
	}

}
