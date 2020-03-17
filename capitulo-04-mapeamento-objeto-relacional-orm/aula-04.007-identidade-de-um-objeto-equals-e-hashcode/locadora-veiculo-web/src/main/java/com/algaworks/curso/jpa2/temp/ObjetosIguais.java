/**
 * 
 */
package com.algaworks.curso.jpa2.temp;

import com.algaworks.curso.jpa2.modelo.Fabricante;

/**
 * @author SEMPR
 *
 */
public class ObjetosIguais {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Fabricante f1 = new Fabricante();
		f1.setCodigo(1L);
		
		Fabricante f2 = new Fabricante();
//		Fabricante f2 = f1;
		/** f2.setCodigo(2l); **/
//		f2.setCodigo(1l);
		f2.setCodigo(2L);
//		f2.setCodigo(20L);
		
		System.out.println("f1.equals(f2)? " + f1.equals(f2));
		System.out.println(f1.getCodigo());
		System.out.println(f2.getCodigo());
	}

}
