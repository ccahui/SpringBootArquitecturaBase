package com.example.demo2.utils;

import java.awt.Color;
import java.io.ByteArrayInputStream;
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
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.DottedLineSeparator;
import com.lowagie.text.pdf.draw.LineSeparator;


public class PdfBuilder {
	  private static final int LINE_GAP = 2;
	    private static final float LINE_WIDTH = 0.5f;
	
	private Document document;
	private ByteArrayOutputStream outputStream;
	private String pdfTitulo;

	public PdfBuilder(String name) {
		this.pdfTitulo = name;
		preparedDocument();
	}

	private void preparedDocument() {
		document = new Document();
		outputStream = new ByteArrayOutputStream();
		PdfWriter.getInstance(document, outputStream);

		metadata();

		HeaderFooter header = PdfComponentesComunes.header();
		HeaderFooter footer = PdfComponentesComunes.footer();

		document.setHeader(header);
		document.setFooter(footer);
		
		document.open();		
	}
	
	  public PdfBuilder line() {
	        DottedLineSeparator separator = new DottedLineSeparator();
	        separator.setGap(LINE_GAP);
	        separator.setLineWidth(LINE_WIDTH);
	        Paragraph p1 = new Paragraph(new Chunk(separator));
	        p1.setSpacingBefore(-10);
		    p1.setSpacingAfter(0);
	        
	        document.add(p1);
	        return this;
	    }
	public ByteArrayInputStream build() {
		this.document.close();
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
	  public PdfBuilder paragraphBold(String text) {
		  Paragraph p = new Paragraph(text, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD));
	      this.document.add(p);
		  return this;
	 }

	 public PdfBuilder paragraph(String text) {
	        this.document.add(new Paragraph(text, FontFactory.getFont(FontFactory.HELVETICA, 11)));
	        return this;
	 }
	 
	 public PdfTableBuilder table(float... widths) {
	        return new PdfTableBuilder(this, this.document, widths);
	    }
	 

	private void metadata() {
		document.addTitle(pdfTitulo);
		document.addAuthor("Kristian");
		document.addSubject("");
		document.addKeywords("");
		document.addCreator("");
	}

	
}
