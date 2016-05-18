package src;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class Principal {
	static XYSeriesCollection collection = new XYSeriesCollection();
    static XYSeries series = new XYSeries("");
    static XYDataset dataset1;
	
	public static void main(String[] args) throws FileNotFoundException {
		JsonParser parser = new JsonParser();
		FileReader fr = new FileReader("../../C/integralesV2/prueba1.json");
		JsonElement datos1 = parser.parse(fr);
		//recogerDatosX(datos1);
		
		fr = new FileReader("../../C/integralesV2/prueba2.json");
		JsonElement datos2 = parser.parse(fr);
		dataset1 = crearDataset(datos1, datos2);
		
		JFreeChart chart = ChartFactory.createScatterPlot("Titulo", "X", "Y", dataset1);
		ChartPanel panel = new ChartPanel(chart);
		JFrame ventana = new JFrame("1");
		ventana.setVisible(true);
		ventana.setSize(1200, 1200);
		ventana.add(panel);
		
		
	}
	
	public static XYDataset crearDataset (JsonElement datos1, JsonElement datos2){
		double x ,y;
		
		JsonArray array1 = datos1.getAsJsonArray();
        java.util.Iterator<JsonElement> iter1 = array1.iterator();
        JsonArray array2 = datos2.getAsJsonArray();
        java.util.Iterator<JsonElement> iter2 = array2.iterator();
        
        while (iter1.hasNext()) {
            JsonElement entrada = iter1.next();
            JsonPrimitive valor = entrada.getAsJsonPrimitive();
    		//dataset1.setValue(valor.getAsDouble(), "Y", ""+x[i]);
    		x = valor.getAsDouble();
    		entrada = iter2.next();
    		valor = entrada.getAsJsonPrimitive();
    		y = valor.getAsDouble();
    		series.add(x,y);
        }
        collection.addSeries(series);
        return collection;
	}
}
