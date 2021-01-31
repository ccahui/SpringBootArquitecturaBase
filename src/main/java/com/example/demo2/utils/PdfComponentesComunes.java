package com.example.demo2.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class PdfComponentesComunes {
	
	private static final String HEADER = "Universidad Nacional de San Agustin";
	private static String urlUnsa = "src/main/resources/UNSA1.png";


	public static HeaderFooter footer() {
		String texto = "";
		HeaderFooter footer = new HeaderFooter(new Phrase(texto), true);
		footer.setBorder(Rectangle.NO_BORDER);
		footer.setAlignment(Element.ALIGN_CENTER);
		return footer;
	}

	public static HeaderFooter header() {
		Chunk logo = logo();
		Chunk headerTexto = headerTexto();
		Phrase contenidoHeader = new Phrase();

		contenidoHeader.add(logo);
		contenidoHeader.add(headerTexto);

		HeaderFooter headerB = new HeaderFooter(contenidoHeader, false);
		headerB.setAlignment(HeaderFooter.ALIGN_CENTER);
		return headerB;
	}

	private static Chunk logo() {
		Image image1 = null;
		try {
			image1 = Image.getInstance(urlUnsa);
			image1.scaleAbsolute(14, 18);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Chunk imageChunk = new Chunk(image1, 0, -3.77f);

		return imageChunk;
	}

	private static Chunk headerTexto() {
		String headerText = "\t\t" + HEADER.toUpperCase();
		Chunk chunk = new Chunk(headerText, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.ITALIC));
		return chunk;
	}
	
}
