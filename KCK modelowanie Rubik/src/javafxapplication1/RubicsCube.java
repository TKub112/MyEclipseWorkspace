package javafxapplication1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Simple implementation of the Rubik's cube using JavaFX 3D
 * http://stackoverflow.com/questions/34001900/how-to-render-3d-graphics-properly
 * @author JosePereda
 */
public class RubicsCube extends Application {
    
    public static final int RED     = 0;
    public static final int GREEN   = 1;
    public static final int BLUE    = 2;
    public static final int YELLOW  = 3;
    public static final int ORANGE  = 4;
    public static final int WHITE   = 5;
    public static final int GRAY    = 6;
    
    public static final float X_RED     = 0.5f / 7f;
    public static final float X_GREEN   = 1.5f / 7f;
    public static final float X_BLUE    = 2.5f / 7f;
    public static final float X_YELLOW  = 3.5f / 7f;
    public static final float X_ORANGE  = 4.5f / 7f;
    public static final float X_WHITE   = 5.5f / 7f;
    public static final float X_GRAY    = 6.5f / 7f;
    
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    
    @Override
    public void start(Stage primaryStage) {
        Group sceneRoot = new Group();
        Scene scene = new Scene(sceneRoot, 600, 600, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.BLACK);
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-10);
        scene.setCamera(camera);

        PhongMaterial mat = new PhongMaterial();
        // image can be found here http://i.stack.imgur.com/uN4dv.png
        mat.setDiffuseMap(new Image(getClass().getResourceAsStream("palette.png")));

        Group meshGroup = new Group();

        AtomicInteger cont = new AtomicInteger();
        patternFaceF.forEach(p -> {
            MeshView meshP = new MeshView();
            meshP.setMesh(createCube(p));
            meshP.setMaterial(mat);
            Point3D pt = pointsFaceF.get(cont.getAndIncrement());
            meshP.getTransforms().addAll(new Translate(pt.getX(), pt.getY(), pt.getZ()));
            meshGroup.getChildren().add(meshP);
        });
        //animation
        
      //Creating a rotate transition    
        Animation(meshGroup.getChildren().get(4));
        Animation(meshGroup.getChildren().get(5));
        Animation(meshGroup.getChildren().get(6));
        Animation(meshGroup.getChildren().get(7));
        
        
        
        Rotate rotateX = new Rotate(30, 0, 0, 0, Rotate.X_AXIS);
        Rotate rotateY = new Rotate(20, 0, 0, 0, Rotate.Y_AXIS);
        meshGroup.getTransforms().addAll(rotateX, rotateY);
        
        sceneRoot.getChildren().addAll(meshGroup, new AmbientLight(Color.WHITE));

        scene.setOnMousePressed(me -> {
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        scene.setOnMouseDragged(me -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            rotateX.setAngle(rotateX.getAngle()-(mousePosY - mouseOldY));
            rotateY.setAngle(rotateY.getAngle()+(mousePosX - mouseOldX));
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
        });
        
        primaryStage.setTitle("Simple Rubik's Cube - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
      	private void Animation(Node node) {
      		RotateTransition rotateTransition = new RotateTransition(); 
            
            //Setting the duration for the transition 
            rotateTransition.setDuration(Duration.millis(1000)); 
            
            //Setting the node for the transition 
            rotateTransition.setNode(node);       
            
            //Setting the angle of the rotation 
            rotateTransition.setByAngle(-90); 
            
            //Setting the cycle count for the transition 
            rotateTransition.setCycleCount(100000); 
            
            //Setting auto reverse value to false 
            rotateTransition.setAutoReverse(false); 
            
            //Playing the animation 
            rotateTransition.play(); 
		
	}
											// F   R   U   B   L   D
    private static final int[] FLD  = new int[]{BLUE, GRAY, GRAY, GRAY, ORANGE, WHITE};
    //private static final int[] FD   = new int[]{BLUE, GRAY, GRAY, GRAY, GRAY, WHITE};
    private static final int[] FRD  = new int[]{BLUE, RED, GRAY, GRAY, GRAY, WHITE};
    //private static final int[] FL   = new int[]{BLUE, GRAY, GRAY, GRAY, ORANGE, GRAY};
    //private static final int[] F    = new int[]{BLUE, GRAY, GRAY, GRAY, GRAY, GRAY};
    //private static final int[] FR   = new int[]{BLUE, RED, GRAY, GRAY, GRAY, GRAY};
    private static final int[] FLU  = new int[]{BLUE, GRAY, YELLOW, GRAY, ORANGE, GRAY};
    //private static final int[] FU   = new int[]{BLUE, GRAY, YELLOW, GRAY, GRAY, GRAY};
    private static final int[] FRU  = new int[]{BLUE, RED, YELLOW, GRAY, GRAY, GRAY};
    
    private static final Point3D pFLD   = new Point3D(-0.6,  0.6, -0.6);
    //private static final Point3D pFD    = new Point3D(   0,  1.1, -1.1);
    private static final Point3D pFRD   = new Point3D( 0.6,  0.6, -0.6);
    //private static final Point3D pFL    = new Point3D(-1.1,    0, -1.1);
    //private static final Point3D pF     = new Point3D(   0,    0, -1.1);
    //private static final Point3D pFR    = new Point3D( 1.1,    0, -1.1);
    private static final Point3D pFLU   = new Point3D(-0.6, -0.6, -0.6);
    //private static final Point3D pFU    = new Point3D(   0, -1.1, -1.1);
    private static final Point3D pFRU   = new Point3D( 0.6, -0.6, -0.6);
    
    //private static final int[] CLD  = new int[]{GRAY, GRAY, GRAY, GRAY, ORANGE, WHITE};
    //private static final int[] CD   = new int[]{GRAY, GRAY, GRAY, GRAY, GRAY, WHITE};
    //private static final int[] CRD  = new int[]{GRAY, RED, GRAY, GRAY, GRAY, WHITE};
    //private static final int[] CL   = new int[]{GRAY, GRAY, GRAY, GRAY, ORANGE, GRAY};
    //private static final int[] C    = new int[]{GRAY, GRAY, GRAY, GRAY, GRAY, GRAY};
    //private static final int[] CR   = new int[]{GRAY, RED, GRAY, GRAY, GRAY, GRAY};
    //private static final int[] CLU  = new int[]{GRAY, GRAY, YELLOW, GRAY, ORANGE, GRAY};
    //private static final int[] CU   = new int[]{GRAY, GRAY, YELLOW, GRAY, GRAY, GRAY};
    //private static final int[] CRU  = new int[]{GRAY, RED, YELLOW, GRAY, GRAY, GRAY};
    
    //private static final Point3D pCLD   = new Point3D(-1.1,  1.1, 0);
    //private static final Point3D pCD    = new Point3D(   0,  1.1, 0);
    //private static final Point3D pCRD   = new Point3D( 1.1,  1.1, 0);
    //private static final Point3D pCL    = new Point3D(-1.1,    0, 0);
    //private static final Point3D pC     = new Point3D(   0,    0, 0);
    //private static final Point3D pCR    = new Point3D( 1.1,    0, 0);
    //private static final Point3D pCLU   = new Point3D(-1.1, -1.1, 0);
    //private static final Point3D pCU    = new Point3D(   0, -1.1, 0);
    //private static final Point3D pCRU   = new Point3D( 1.1, -1.1, 0);
    
    private static final int[] BLD  = new int[]{GRAY, GRAY, GRAY, GREEN, ORANGE, WHITE};
    //private static final int[] BD   = new int[]{GRAY, GRAY, GRAY, GREEN, GRAY, WHITE};
    private static final int[] BRD  = new int[]{GRAY, RED, GRAY, GREEN, GRAY, WHITE};
    //private static final int[] BL   = new int[]{GRAY, GRAY, GRAY, GREEN, ORANGE, GRAY};
    //private static final int[] B    = new int[]{GRAY, GRAY, GRAY, GREEN, GRAY, GRAY};
    //private static final int[] BR   = new int[]{GRAY, RED, GRAY, GREEN, GRAY, GRAY};
    private static final int[] BLU  = new int[]{GRAY, GRAY, YELLOW, GREEN, ORANGE, GRAY};
    //private static final int[] BU   = new int[]{GRAY, GRAY, YELLOW, GREEN, GRAY, GRAY};
    private static final int[] BRU  = new int[]{GRAY, RED, YELLOW, GREEN, GRAY, GRAY};
    
    private static final Point3D pBLD   = new Point3D(-0.6,  0.6, 0.6);
    //private static final Point3D pBD    = new Point3D(   0,  1.1, 1.1);
    private static final Point3D pBRD   = new Point3D( 0.6,  0.6, 0.6);
    //private static final Point3D pBL    = new Point3D(-1.1,    0, 1.1);
    //private static final Point3D pB     = new Point3D(   0,    0, 1.1);
    //private static final Point3D pBR    = new Point3D( 1.1,    0, 1.1);
    private static final Point3D pBLU   = new Point3D(-0.6, -0.6, 0.6);
    //private static final Point3D pBU    = new Point3D(   0, -1.1, 1.1);
    private static final Point3D pBRU   = new Point3D( 0.6, -0.6, 0.6);
    
    private static final List<int[]> patternFaceF = Arrays.asList(
            FLD, FRD, FLU, FRU,
            //CLD, CRD, /*CLU, CRU,
            BLD, BRD, BLU, BRU);
    
    private static final List<Point3D> pointsFaceF = Arrays.asList(
            pFLD, pFRD, pFLU, pFRU,
            //pCLD, pCRD, pCLU, pCRU,
            pBLD, pBRD, pBLU, pBRU);
    
    private TriangleMesh createCube(int[] face) {
        TriangleMesh m = new TriangleMesh();

        // POINTS
        m.getPoints().addAll(
             0.5f,  0.5f,  0.5f,
             0.5f, -0.5f,  0.5f,
             0.5f,  0.5f, -0.5f,
             0.5f, -0.5f, -0.5f,
            -0.5f,  0.5f,  0.5f,
            -0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f
        );

        // TEXTURES
        m.getTexCoords().addAll(
            0f/7, 0, 1f/7, 0, 1f/7, 1, 0f/7, 1, //0, 1, 2, 3
            1f/7, 0, 2f/7, 0, 2f/7, 1, 1f/7, 1, //4, 5, 6, 7
            2f/7, 0, 3f/7, 0, 3f/7, 1, 2f/7, 1, //8, 9, 10, 11
            3f/7, 0, 4f/7, 0, 4f/7, 1, 3f/7, 1, //12, 13, 14, 15
            4f/7, 0, 5f/7, 0, 5f/7, 1, 4f/7, 1, //16, 17, 18, 19
            5f/7, 0, 6f/7, 0, 6f/7, 1, 5f/7, 1, //20, 21, 22, 23
            6f/7, 0, 7f/7, 0, 7f/7, 1, 6f/7, 1 //24, 25, 26, 27
        );

            // FACES
        m.getFaces().addAll(
            2,4*face[0]+1,3,4*face[0]+2,6,4*face[0]+0,      // F      
            3,4*face[0]+2,7,4*face[0]+3,6,4*face[0]+0,  

            0,face[1],1,face[1],2,face[1],      // R     
            2,face[1],1,face[1],3,face[1],         

            1,face[2],5,face[2],3,face[2],      // U   
            5,face[2],7,face[2],3,face[2],

            0,face[3],4,face[3],1,face[3],      // B      
            4,face[3],5,face[3],1,face[3],       

            4,face[4],6,face[4],5,face[4],      // L      
            6,face[4],7,face[4],5,face[4],    

            0,face[5],2,face[5],4,face[5],      // D      
            2,face[5],6,face[5],4,face[5]         
        );
        return m;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}