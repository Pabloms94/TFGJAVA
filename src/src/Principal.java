package src;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class Principal {
	
	static DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
	static double []x = new double[60];
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		JsonParser parser = new JsonParser();
		FileReader fr = new FileReader("../../C/integralesV2/prueba1.json");
		JsonElement datos1 = parser.parse(fr);
		recogerDatosX(datos1);
		
		fr = new FileReader("../../C/integralesV2/prueba2.json");
		JsonElement datos2 = parser.parse(fr);
		recogerDatosY(datos2);
		
		JFreeChart chart = ChartFactory.createLineChart("Titulo", "X", "Y", dataset1);
		ChartPanel panel = new ChartPanel(chart);
		JFrame ventana = new JFrame("1");
		ventana.setVisible(true);
		ventana.setSize(1200, 1200);
		ventana.add(panel);
		
		
	}
	
	public static void recogerDatosX (JsonElement datos){
		int i = 0;
		
		JsonArray array = datos.getAsJsonArray();
        java.util.Iterator<JsonElement> iter = array.iterator();
        while (iter.hasNext()) {
            JsonElement entrada = iter.next();
            JsonPrimitive valor = entrada.getAsJsonPrimitive();
    		x[i] = valor.getAsDouble();
    		i++;
        }
	}
	
	public static void recogerDatosY (JsonElement datos){
		int i = 0;
		JsonArray array = datos.getAsJsonArray();
        java.util.Iterator<JsonElement> iter = array.iterator();
        while (iter.hasNext()) {
            JsonElement entrada = iter.next();
            JsonPrimitive valor = entrada.getAsJsonPrimitive();
    		dataset1.setValue(valor.getAsDouble(), "Y", ""+x[i]);
    		i++;
        }
	}
}
