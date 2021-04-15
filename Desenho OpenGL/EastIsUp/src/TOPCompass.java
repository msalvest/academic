import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class TOPCompass implements GLEventListener {
	
	public GLAutoDrawable coresRGB (GLAutoDrawable key, String cor){
		final GL2 gl = key.getGL().getGL2(); // key = recebe "drawable" passado no metodo display
		
		switch (cor) {
		case "amarelo":
			gl.glColor3d(0.97, 0.89, 0.00);
			break;
		case "verde":
			gl.glColor3d(0.26, 0.29, 0.15);
			break;
		case "preto":
			gl.glColor3d(0.00, 0.00, 0.00);
			break;
		case "prata":
			gl.glColor3d(0.91, 0.91, 0.91);
			break;
		case "cinza":
			gl.glColor3d(0.41, 0.41, 0.41);
			break;		
		}		
		return key;			
	}
	
	public void circulo (GLAutoDrawable key, String cor, double cx, double cy, double r) {
		TOPCompass metodo = new TOPCompass();
		final GL2 gl = key.getGL().getGL2(); // key = recebe "drawable" passado no metodo display		
				
		float ang, x, y;	
		
		metodo.coresRGB(key, cor);		
		gl.glBegin(GL2.GL_POLYGON);		
		for (int i = 0; i < 360; i++) {
			
			ang = (float) ((i * Math.PI)/ 180.0);
			x = (float) (cx + (Math.cos(ang) * r)); // cx, r = coordenada centro x, raio x
			y = (float) (cy + (Math.sin(ang) * r)); // cy, r = coordenada centro y, raio y		
			gl.glVertex2f(x,y);
			
		}		
		gl.glEnd();				
	}
	
	public void contornoCirculo (GLAutoDrawable key, String cor, double e, double cx, double cy, double r) {
		TOPCompass metodo = new TOPCompass();
		final GL2 gl = key.getGL().getGL2(); // key = recebe "drawable" passado no metodo display
		
		float ang, x, y;
				
		metodo.coresRGB(key, cor);		
		
		gl.glLineWidth((float) e); // espessura da linha	
		gl.glBegin(GL2.GL_LINE_LOOP);
		
		for (int i = 0; i < 360; i++) {
			
			ang = (float) ((i * Math.PI)/ 180.0);
			x = (float) (cx + (Math.cos(ang) * r)); // cx, r = coordenada centro x, raio x
			y = (float) (cy + (Math.sin(ang) * r)); // cy, r = coordenada centro y, raio y		
			gl.glVertex2f(x,y);
		}		
		gl.glEnd();		
	}	
		
	public void linha(GLAutoDrawable key, String cor, double e, double x1, double y1, double x2, double y2) {
		TOPCompass metodo = new TOPCompass();
		final GL2 gl = key.getGL().getGL2(); // key = recebe "drawable" passado no metodo display	
		
		gl.glLineWidth((float) e); 	// e = espessura da linha
		gl.glBegin(GL2.GL_LINES);		
		metodo.coresRGB(key, cor);		
		gl.glVertex2d(x1, y1);		// Ponto 1
		gl.glVertex2d(x2, y2);		// Ponto 2		
		gl.glEnd();			
	}
	
	public void triangulo(GLAutoDrawable key, String cor, double x1, double y1, double x2, double y2, double x3, double y3) {
		TOPCompass metodo = new TOPCompass();
		final GL2 gl = key.getGL().getGL2(); // key = recebe "drawable" passado no metodo display		
		        
		gl.glBegin(GL2.GL_TRIANGLES);
		metodo.coresRGB(key, cor);
		gl.glVertex2d(x1, y1);	
		gl.glVertex2d(x2, y2);	
		gl.glVertex2d(x3, y3);				
		gl.glEnd();							
	}
	
	public void contornoTriangulo (GLAutoDrawable key, String cor, double e, double x1, double y1, double x2, double y2, double x3, double y3) {
		
		TOPCompass metodo = new TOPCompass();		
		final GL2 gl = key.getGL().getGL2(); // key = recebe "drawable" passado no metodo display
		
		gl.glLineWidth((float) e);
		gl.glBegin(GL2.GL_LINE_LOOP);
		metodo.coresRGB(key, cor);
		gl.glVertex2d(x1, y1);	
		gl.glVertex2d(x2, y2);	
		gl.glVertex2d(x3, y3);		
		gl.glEnd();		
	}
	
	public void retangulo(GLAutoDrawable key, String cor, double x1, double y1, double x2, double y2) {
		
		TOPCompass metodo = new TOPCompass();		
		final GL2 gl = key.getGL().getGL2(); // key = recebe "drawable" passado no metodo display
		
		metodo.coresRGB(key, cor);
		gl.glRectd(x1, y1,  x2, y2); // x1, y1 = primeiro ponto | x2, y2 = ponto oposto ao primeiro

	}				
		
	public GLAutoDrawable rotacao(GLAutoDrawable key, String desenho, String cor, double cont, double ang, 
			double e, double x1, double y1, double x2, double y2, double x3, double y3) {
		
		TOPCompass metodo = new TOPCompass();
		final GL2 gl = key.getGL().getGL2(); // key = recebe "drawable" passado no metodo display
		
		switch (desenho) {		
			case "linha":			
				gl.glPushMatrix();
				for(int i = 0; i <= cont; i++) { // cont = quantas vezes sera copiado o desenho
					gl.glRotated(ang, 0.0, 0.0, 1.0); //ang = angulo entre os desenhos
					metodo.linha(key, cor, e, x1, y1, x2, y2);		
				}	
			    gl.glPopMatrix();		   
				break;
				
			case "triangulo":			
				gl.glPushMatrix();
				for(int i = 0; i <= cont; i++) {
					gl.glRotated(ang, 0.0, 0.0, 1.0);
					metodo.triangulo(key, cor, x1, y1, x2, y2, x3, y3);		
				}	
			    gl.glPopMatrix();
			    break;
				
			case "contornoTriangulo":			
				gl.glPushMatrix();
				for(int i = 0; i <= cont; i++) {
					gl.glRotated(ang, 0.0, 0.0, 1.0);
					metodo.contornoTriangulo(key, cor, e, x1, y1, x2, y2, x3, y3);		
				}	
			    gl.glPopMatrix();		   
				break;			
		}	
					   	   
	   return key;		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
		final GL2 gl = drawable.getGL().getGL2(); 
		
		//Funcoes para desenhar com as medias em pixels
		gl.glMatrixMode(GL2.GL_PROJECTION);		
		gl.glOrtho(-225, 225, -225, 225, -225, 225);
		
		//Funcoes para renderizar linha e poligonos
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL2.GL_BLEND);
		gl.glEnable(GL2.GL_LINE_SMOOTH);
		gl.glHint(GL2.GL_LINE_SMOOTH_HINT, GL2.GL_NICEST);
		gl.glEnable(GL2.GL_POLYGON_SMOOTH);
		gl.glHint(GL2.GL_POLYGON_SMOOTH_HINT, GL2.GL_NICEST);			
				
		//Circulo Backgroung
		circulo(drawable, "verde", 0.0, 0.0, 210.5);
		contornoCirculo(drawable, "amarelo", 2, 0.0, 0.0, 210.5);
		
		//Circulos Meio				
		contornoCirculo(drawable, "amarelo", 2, 0, 0, 122);
		contornoCirculo(drawable, "amarelo", 1, 0, 0, 113.5);		
		contornoCirculo(drawable, "amarelo", 1, 0, 0,  92);
		
		// 4 Linhas ao redor do circulo 			
		rotacao(drawable, "linha", "preto", 4, 90, 2, -131, 129, -107, 105, 0, 0);
		
		//24 Linhas ao redor do circulo 		
		rotacao(drawable, "linha", "preto", 24, 15, 0.5, 0, 105, 0, 139, 0, 0);	
		
		//Estrela Bussola
		
			//Menor | Maior solido
			rotacao(drawable, "triangulo", "prata", 4, 90, 0,  18, 18, 0, 140, 0, 0);
			rotacao(drawable, "triangulo", "cinza", 4, 90, 0, -18, 18, 0, 140, 0, 0);
			rotacao(drawable, "triangulo", "prata", 4, 90, 0, -61, 61, 0,  15, 0, 0);
			rotacao(drawable, "triangulo", "cinza", 4, 90, 0, -61, 61, 0, -15, 0, 0);
			
			//Menor | Maior contorno
			rotacao(drawable, "contornoTriangulo", "prata", 4, 90, 1,  18, 18, 0, 140, 0, 0);
			rotacao(drawable, "contornoTriangulo", "cinza", 4, 90, 1, -18, 18, 0, 140, 0, 0);
			rotacao(drawable, "contornoTriangulo", "prata", 4, 90, 1, -61, 61, 0,  15, 0, 0);
			rotacao(drawable, "contornoTriangulo", "cinza", 4, 90, 1, -61, 61, 0, -15, 0, 0);		
		
		//Circulo logo TOP
		circulo(drawable, "preto", 0.0, 0.0, 36); 
		contornoCirculo(drawable, "amarelo", 2, 0.0, 0.0, 36);
		
		//Ponteira Bussola		
		triangulo(drawable, "verde",   5,  0, -5,  0, 0,  70); //Up
		triangulo(drawable, "preto",   5,  0, -5,  0, 0, -70); //Down		
		triangulo(drawable, "amarelo", 2, 44, -2, 44, 0,  70); //Ponta amarela
					
		contornoTriangulo(drawable, "verde",   1, 5,  0, -5,  0, 0, 70); //Up
		contornoTriangulo(drawable, "preto",   1, 5,  0, -5,  0, 0,-70); //Down		
		contornoTriangulo(drawable, "amarelo", 1, 2, 44, -2, 44, 0, 70); //Ponta amarela
						
		//Linhas logo TOP " ||-//"
		linha(drawable, "amarelo", 2, -20, 17, -20, -17);
		linha(drawable, "amarelo", 2, -12, 17, -12, -17);			
		linha(drawable, "amarelo", 2,   5,  0,  -5,   0);
		linha(drawable, "amarelo", 2,  18, 15,   7, -10);
		linha(drawable, "amarelo", 2,  26, 15,  15, -10);
		
		//EAST | NORTH | WEST | SOUTH		
			//East
			retangulo(drawable, "amarelo", -14, 181, -9, 166); // Vertical Lateral Esq.
			retangulo(drawable, "amarelo",  -2, 192,  3, 151); 	   // Vertical Meio
			retangulo(drawable, "amarelo",   9, 181, 14, 166);   // Vertical Lateral Dir.
			retangulo(drawable, "amarelo", -14, 171, 14, 166);   // Horizontal
			
			//North
			retangulo(drawable, "amarelo", -185,  10, -157, -10); //Tam. total
			triangulo(drawable, "verde",   -163,  10, -175,  10, -163, -5); //Down
			triangulo(drawable, "verde",   -179, -10, -179,   5, -167, -10); //Up	
			
			contornoTriangulo(drawable, "verde", 1, -163, 10, -175, 10, -163, -5); //Down
			contornoTriangulo(drawable, "verde", 1, -179, -10, -179, 5, -167, -10); //Up	
		 	
			//West
			retangulo(drawable, "amarelo", -19, -161,  19, -181); //Tam. total	
			triangulo(drawable, "verde",   -19, -181, -12, -181, -19, -161); //Quina Esq
			triangulo(drawable, "verde",    -3, -161, -13, -161,  -8, -176); //Triangulo Inv. 01
			triangulo(drawable, "verde",    13, -161,   3, -161,   8, -176); //Triangulo Inv. 02
			triangulo(drawable, "verde",     5, -181,   0, -168,  -5, -181); //Triangulo Up Meio
			triangulo(drawable, "verde",    12, -181,  19, -161,  19, -181); //Quina Dir
			
			contornoTriangulo(drawable, "verde", 1, -19, -181, -12, -181, -19, -161); //Quina Esq
			contornoTriangulo(drawable, "verde", 1,  -3, -161, -13, -161,  -8, -176); //Triangulo Inv. 01
			contornoTriangulo(drawable, "verde", 1,  13, -161,   3, -161,   8, -176); //Triangulo Inv. 02
			contornoTriangulo(drawable, "verde", 1,   5, -181,   0, -168,  -5, -181); //Triangulo Up Meio
			contornoTriangulo(drawable, "verde", 1,  12, -181,  19, -161,  19, -181); //Quina Dir
			
			//South
			retangulo(drawable, "amarelo", 158,  11, 184, -11); //Tam. total	
			retangulo(drawable, "verde",   164,  6, 178,  2); //Corte Meio Up
			retangulo(drawable, "verde",   178,  4, 184,  2); //Corte Lateral Dir
			retangulo(drawable, "verde",   164, -2, 178, -6); //Corte Meio Down
			retangulo(drawable, "verde",   158, -2, 164, -4); //Corte Lateral Esq.
			
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile); 
		
		// The canvas       
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		TOPCompass basicFrame = new TOPCompass();
		glcanvas.addGLEventListener(basicFrame);       
		glcanvas.setSize(450, 480);
		
		//creating frame       
		final JFrame frame = new JFrame ("E A S T   I S   U P"); 		
		//adding canvas to it 
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true); 


	}

}
