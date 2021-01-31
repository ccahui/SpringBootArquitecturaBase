package com.example.demo2.utils;


import java.awt.Color;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;


public class PdfTableBuilder {

    private static final Font FONT_BODY = FontFactory.getFont(FontFactory.HELVETICA, 11);
    private static final Font FONT_HEADER = FontFactory.getFont(FontFactory.HELVETICA, 10.5f, Font.BOLD);
    private static final int PADDING = 3;
    private final PdfBuilder pdfBuilder;

    private final Document document;

    private final  Table table;


    PdfTableBuilder(PdfBuilder pdfbuilder, Document document, float... widths) {
        int colums = widths.length;
    	this.pdfBuilder = pdfbuilder;
        this.document = document;
        this.table = new Table(colums);
        table.setWidths(widths);
        table.setPadding(PADDING);
    }
    public PdfTableBuilder tableSetWidth(int width) {
    	this.table.setWidth(width);
    	return this;
    }
    

    public PdfTableBuilder tableRowHeader(String... headers) {
    	 
    	for (String header : headers) {
    		 Chunk headerText = new Chunk(header, FONT_HEADER);
             
    		 Cell c1 = new Cell(headerText);
             c1.setHeader(true);
             c1.setBackgroundColor(new Color(207, 226, 243));
             table.addCell(c1);
        }
    	table.endHeaders();
        return this;
    }

    public PdfTableBuilder tableRow(Object... cells) {
    	for (Object cell : cells) {
    		 Phrase TEXT = new Phrase(cell.toString(), FONT_BODY);
            this.table.addCell(TEXT);
        }
        return this;
    }


    public PdfBuilder closeTable() {
        this.document.add(this.table);
        return this.pdfBuilder;
    }

}
