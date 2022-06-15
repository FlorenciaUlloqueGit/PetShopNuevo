package com.thesis.FlorenciaUlloque.UTN.Util;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.thesis.FlorenciaUlloque.UTN.Dtos.dtosProductos.ProductoReporteBolsaCerrada;
import com.thesis.FlorenciaUlloque.UTN.entiities.Stock;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ReporteStockPDF {
    private List<Stock> listaStocks;

    public ReporteStockPDF(List<Stock> listaStocks) {
        this.listaStocks = listaStocks;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.orange);
        cell.setPadding(6);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.black);
        cell.setPhrase(new Phrase("Producto", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Categoría", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Peso", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unidad de medida ", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Forma de venta", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad", font));
        table.addCell(cell);




    }
    private void  writeTableData(PdfPTable table){

        for(Stock stock: listaStocks ) {
            table.addCell(stock.getProducto().getNombre());
            table.addCell(stock.getProducto().getCategoria().getNombre());
            table.addCell(String.valueOf(stock.getProducto().getPesoNeto()));
            table.addCell(stock.getProducto().getUnidadMedida().getNombre());
            table.addCell(stock.getProducto().getFormaVenta().getNombre());
            table.addCell(String.valueOf(stock.getCantidad()));

            }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
      //  Page pageSize = new Page();

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Paragraph titulo = new Paragraph("Stock de productos");

        document.add(new Paragraph(titulo));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);


        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
