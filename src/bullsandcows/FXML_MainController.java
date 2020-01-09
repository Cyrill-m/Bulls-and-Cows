package bullsandcows;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Kiryl Matusevich
 *  
 */
public class FXML_MainController implements Initializable {

    @FXML
    private ToggleButton tbtnEN;
    @FXML
    private ToggleGroup tbtnLang;
    @FXML
    private ToggleButton tbtnRU;
    @FXML
    private ToggleButton tbtnDCSS;
    @FXML
    private ToggleGroup tbtnCSS;
    @FXML
    private ToggleButton tbtnWCSS;
    @FXML
    private Button btnNewGame;
    @FXML
    private Label lbNumber;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnEnter;
    @FXML
    private Label lbField;
    @FXML
    private Label lbInfo;
    @FXML
    private AnchorPane rootMain;    
    
    private static String NUMBER;       //��������� �����
    private static String REPORT_RU;    //����������(���) ����
    private static String REPORT_EN;    //����������(���) ����
    
    private static boolean GAME_STAT;   //���� ����
    private static int STEP_COUNT;      //������� �����
    
    private ResourceBundle bundleRU;    //����������� �������� �����
    private ResourceBundle bundleEN;    //����������� ����������� �����
    private ResourceBundle bundleSEL;   //��������� �����������
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //������������� ��������� ����������
        GAME_STAT = false;
        STEP_COUNT = 1;
        REPORT_RU = "";
        REPORT_EN = "";
        lbNumber.setText("");
        
        // �������� �������� �����������
        bundleRU = ResourceBundle.getBundle("locale.Lang", new Locale("ru"));
        bundleEN = ResourceBundle.getBundle("locale.Lang", new Locale("en"));
        
        //��������� ��������� �����������
        if (tbtnEN.isSelected()){
            bundleSEL = bundleEN;
        } else bundleSEL = bundleRU;
        setLocale(bundleSEL);  
        
        rootMain.setId("rootw");
        lbNumber.setId("labelw");
        lbInfo.setId("labelw");
        lbField.setId("labelw");
    }    

    //������ ����� ���� ������� btnNewGame
    @FXML
    private void btnNewGameAction(ActionEvent event) {
        lbNumber.setText("");
        lbInfo.setText("");
                        
        GAME_STAT = true;
        STEP_COUNT = 1;
        REPORT_RU = "";
        REPORT_EN = "";
        //��������� ���������� ����� ��� ������ ����� ����
        String s = "";
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 4; i++) {
            while (true){
                int x = (int)(Math.random()*10);
                if (nums[x] == x){
                    s = s + x;
                    nums[x] = -1;
                    break;
                }
            }
        }
        NUMBER = s;
        lbInfo.setText(bundleSEL.getString("lb_move"));
        lbField.setText("");
        //lbInfo.setText(NUMBER);
    }

    //��������� ������� �������� ������ 0..9
    @FXML
    private void btnNumberAction(ActionEvent event) {
        //����� � ��������� ������� lbInfo, ���� ���� �� ������
        if (!GAME_STAT) {
            //lbInfo.setTextFill(Color.RED);
            return;
        }
        //����� ��� ������� ������ ������ 4 ����
        if (lbNumber.getText().length() == 4) {
            return;
        }
        //���������� ����� � ����� � lbNumber
        String s = ((Button)event.getSource()).getText();
        s = lbNumber.getText() + s;
        lbNumber.setText(s);
    }

    //������� ���� ����� ����� ������� btnClear
    @FXML
    private void btnClearAction(ActionEvent event) {
        lbNumber.setText("");        
    }

    //��������� ����� ������ ����� (������� ������ btnEnter)
    @FXML
    private void btnEnterAction(ActionEvent event) {
        if ((lbNumber.getText().length() == 4) & GAME_STAT) {
            int bull = 0, cow = 0;
            String str = lbNumber.getText();
            for (int i = 0; i < 4; i++) {
                if (NUMBER.charAt(i) == str.charAt(i)) bull++;
                if ((str.charAt(i) != NUMBER.charAt(i)) && 
                        ((str.charAt(i) == NUMBER.charAt(0)) || 
                        (str.charAt(i) == NUMBER.charAt(1)) || 
                        (str.charAt(i) == NUMBER.charAt(2)) || 
                        (str.charAt(i) == NUMBER.charAt(3))) ) cow++;
            }
            REPORT_RU = REPORT_RU + STEP_COUNT + ":  " + str + "  �: " + bull + "  �: " + cow +"\n";
            REPORT_EN = REPORT_EN + STEP_COUNT + ":  " + str + "  B: " + bull + "  C: " + cow +"\n";
            //�������� ��������
            if (bull == 4){
                REPORT_RU = REPORT_RU + "�� ��������!!!";
                REPORT_EN = REPORT_EN + "YOU WIN!!!";
                lbInfo.setText(bundleSEL.getString("lb_start"));
                GAME_STAT = false;
            }
            //�������� ������ �����
            if ((STEP_COUNT == 10) & (bull != 4)){
                REPORT_RU = REPORT_RU + "�� ���������!!!";
                REPORT_EN = REPORT_EN + "YOU LOSE!!!";
                lbInfo.setText(bundleSEL.getString("lb_start"));
                GAME_STAT = false;
            }
            if (tbtnEN.isSelected()){
                lbField.setText(REPORT_EN);
            } else lbField.setText(REPORT_RU);
            
            lbNumber.setText("");
            STEP_COUNT++;
        }
    }

    //��������� �������� ����������� (������ tbtnEN, tbtnRU)
    @FXML
    private void localeAction(ActionEvent event) {
        if (tbtnEN.isSelected()){
            bundleSEL = bundleEN;
        } else bundleSEL = bundleRU;
        setLocale(bundleSEL);
    }
    
    //��������� �������� �����������
    private void setLocale(ResourceBundle bundle){
        //����������� ������
        btnNewGame.setText(bundle.getString("new_game"));
        btnClear.setText(bundle.getString("clear"));
        btnEnter.setText(bundle.getString("enter"));
        tbtnWCSS.setText(bundle.getString("css_w"));
        tbtnDCSS.setText(bundle.getString("css_b"));
        //����������� ������ ����� ���� ���� ����
        if (GAME_STAT){
            lbInfo.setText(bundle.getString("lb_move"));
            if (tbtnEN.isSelected()){                
                lbField.setText(REPORT_EN);
            } else lbField.setText(REPORT_RU);
        }
        //����������� ������ ����� ���� ���� ���
        if (!GAME_STAT){
            lbInfo.setText(bundle.getString("lb_start"));
            lbField.setText(bundle.getString("lb_field_start"));
        }
    }

    @FXML
    private void actionDCSS(ActionEvent event) {
        rootMain.setId("rootd");
        lbNumber.setId("labeld");
        lbInfo.setId("labeld");
        lbField.setId("labeld");
    }

    @FXML
    private void actionWCSS(ActionEvent event) {
        rootMain.setId("rootw");
        lbNumber.setId("labelw");
        lbInfo.setId("labelw");
        lbField.setId("labelw");
    }
    
}
