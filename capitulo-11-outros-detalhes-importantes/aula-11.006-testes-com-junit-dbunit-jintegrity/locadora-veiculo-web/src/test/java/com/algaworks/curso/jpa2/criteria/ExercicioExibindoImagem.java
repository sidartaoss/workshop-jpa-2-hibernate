/**
 * 
 */
package com.algaworks.curso.jpa2.criteria;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.algaworks.curso.jpa2.modelo.Carro;

/**
 * @author SEMPR
 *
 */
public class ExercicioExibindoImagem {

	private static EntityManagerFactory factory;

	private EntityManager manager;

	@BeforeClass
	public static void init() {
		factory = Persistence.createEntityManagerFactory("locadoraVeiculoPU");
	}

	@Before
	public void setUp() {
		this.manager = factory.createEntityManager();

	}

	@After
	public void tearDown() {
		this.manager.close();
	}

	@Test
	public void buscarFoto() throws IOException {
		Carro carro = this.manager.find(Carro.class, 14L);

		if (carro.getFoto() != null) {
			BufferedImage img = ImageIO.read(new ByteArrayInputStream(carro.getFoto()));
			JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(img)));
		} else {
			System.out.println("Carro nao possui foto.");
		}
	}
}
