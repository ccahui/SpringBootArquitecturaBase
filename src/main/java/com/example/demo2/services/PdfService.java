package com.example.demo2.services;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.GroupLayout.Alignment;

import org.hibernate.boot.Metadata;

import com.example.demo2.models.Post;
import com.example.demo2.utils.PdfBuilder;
import com.example.demo2.utils.PdfTableBuilder;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Section;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.DottedLineSeparator;
import com.lowagie.text.pdf.draw.LineSeparator;

public class PdfService {

	private static final int LINE_GAP = 2;
	private static final float LINE_WIDTH = 0.5f;
	private static final String HEADER = "Universidad Nacional de San Agustin";
	private static String LOGO = "src/main/resources/UNSA1.png";

	public static ByteArrayInputStream postReport(List<Post> posts) {
		
		PdfBuilder pdfBuilder = new PdfBuilder("Reporte Posts")
				.paragraphBold("LISTADO DE PUBLICACIONES");
		PdfTableBuilder table = pdfBuilder.table(1, 4, 3)
								.tableRowHeader("ID","TÍTULO","FECHA DE CREACIÓN");
		
		  for(Post post : posts) {
			  	table = table.tableRow(post.getId(), post.getTitle(), post.getCreatedDate());
	        }
			pdfBuilder = table.closeTable();
			return pdfBuilder.build();
	}
	public static ByteArrayInputStream postReport1(List<Post> posts)  {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		PdfWriter.getInstance(document, out);
		
		metadata(document);
	

		 HeaderFooter header = header();		
		 HeaderFooter footer = footer();

		 document.setHeader(header);
		 document.setFooter(footer);

	        
		document.open();
		//HEADER DOCUMENT
		 
		 // headers and footers must be added before the document is opened
       
		//
        /*
        Image image = null;
		try {
			image = Image.getInstance(urlUnsa);
			image.scaleAbsolute(80, 101);
		//	image.setAlignment(Image.ALIGN_RIGHT);
		//	document.add(image);
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// HEADER
		
		PdfPCell cellImage = new PdfPCell(image);
		 cellImage.setBorder(Rectangle.NO_BORDER);
		 
			cellImage.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		 PdfPTable tableLogo = new PdfPTable(2);
		    tableLogo.setWidthPercentage(95);
		    tableLogo.setWidths(new int[]{1, 1});
		    
		    tableLogo.addCell(new Paragraph("This picture was taken at Java One.\nIt shows the iText crew at Java One in 2013."));
		    tableLogo.addCell(cellImage);
		    document.add(tableLogo);
		    Paragraph p1 = new Paragraph(new Chunk(line()));
		    p1.setSpacingBefore(-10);
		    p1.setSpacingAfter(5);
		    document.add(p1) ;;
		    
		    // TABLE
		 Paragraph p = new Paragraph("Listado de Post", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC, new Color(0, 0, 255)));
		//Paragraph p = new Paragraph("Listado de Post" , FontFactory.getFont(FontFactory.HELVETICA, 16));
		p.setIndentationLeft(30f);
		document.add(p);
		
		
		 // demonstrate some table features
        Table table = new Table(3);
        table.setWidths(new float[] {1, 4, 3});
        Font fontTable = FontFactory.getFont(FontFactory.HELVETICA, 10);
        // 2 pixel wide blue border
        //table.setWidth(100);
        
      //  table.setBorderWidth(2);
        //table.setBorderColor(new Color(0, 0, 255));
       table.setPadding(3);
       //table.setSpacing(5);
       Font tableHeader = FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD);
       Chunk header = new Chunk("ID", tableHeader);
       Cell c = new Cell(header);

        c.setHorizontalAlignment(HorizontalAlignment.CENTER);
        c.setHeader(true);
        table.addCell(c);
        
        Chunk header1 = new Chunk("TÍTULO", tableHeader);
        Cell c1 = new Cell(header1);
         c1.setHeader(true);
        table.addCell(c1);
        
        Chunk header2 = new Chunk("FECHA CREACIÓN", tableHeader);
        Cell c2 = new Cell(header2);
         c2.setHeader(true);
        table.addCell(c2);
        
        
        
        table.endHeaders();
        
        
        for(Post post : posts) {
        	Cell idCell = new Cell(post.getId().toString());
        	idCell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        	table.addCell(idCell);
        	table.addCell(new Cell(post.getTitle().toString()));
        	table.addCell(new Cell(post.getCreatedDate().toString()));
        }
        
        document.add(table);
		*/
	
        

		document.newPage();
		  
		 Paragraph title2 = new Paragraph("This is Chapter 2", FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, new Color(0, 0, 255)));
		 Chapter chapter2 = new Chapter(title2, 2);
		 chapter2.setNumberDepth(0);
		 Paragraph someText = new Paragraph("This is some text");
		 chapter2.add(someText);
		 Paragraph title21 = new Paragraph("This is Section 1 in Chapter 2", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(255, 0, 0)));
		 Section section1 = chapter2.addSection(title21);
		 Paragraph someSectionText = new Paragraph("This is some silly paragraph in a chapter and/or section. It contains some text to test the functionality of Chapters and Section.");
		 section1.add(someSectionText);
		 
		 document.add(chapter2);
		 document.newPage(); 
        document.add(new Paragraph("Fin"));

		document.close();
		
		return new ByteArrayInputStream(out.toByteArray());
	}
	
	private static HeaderFooter footer() {
		HeaderFooter footer = new HeaderFooter(new Phrase("This is page: "), true);
        footer.setBorder(Rectangle.NO_BORDER);
        footer.setAlignment(Element.ALIGN_CENTER);
        return footer;
	}
	private static HeaderFooter header() {

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
			image1 = Image.getInstance(LOGO);
			image1.scaleAbsolute(14, 18);
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Chunk imageChunk = new Chunk(image1, 0,-3.77f);

		return imageChunk;
	}
	
	private static Chunk headerTexto() {
		String headerText = "\t\t"+HEADER.toUpperCase();
	    Chunk chunk = new Chunk(headerText,  FontFactory.getFont(FontFactory.HELVETICA, 12, Font.ITALIC));
	    return chunk;
	}
	private static void metadata(Document document) {
		document.addTitle("ReportPost");
		document.addAuthor("Kristian");
		document.addSubject("Post");
		document.addKeywords("openPDF, PDF");
		document.addCreator("My program using iText");
		
	}
	
	public static PdfPCell createTextCell(String text) {
	    PdfPCell cell = new PdfPCell();
	    cell.setPadding(0);
	    Paragraph p = new Paragraph(text);
	    p.setAlignment(Element.ALIGN_LEFT);
	    p.setSpacingAfter(0);
	    p.setSpacingBefore(0);
	    cell.addElement(p);
	    cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	    cell.setBorder(Rectangle.NO_BORDER);
	    return cell;
	}
	
	 public static LineSeparator line() {
	        DottedLineSeparator separator = new DottedLineSeparator();
	        separator.setGap(LINE_GAP);
	        separator.setLineWidth(LINE_WIDTH);

	        return new LineSeparator();

	    }
	
}
