import Services.HomeView.E2eTableService;
import Services.HomeView.HomeServices;
import Services.HomeView.WorkloadTableModel;
import Services.HomeView.WorkloadTableService;
import View.Home;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import Services.HomeView.WorkloadGraph.ChartWorkload;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.IntelliJTheme;
import org.jfree.chart.ChartPanel;

public class Main {

    public static void startExpressContainer() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(
                "docker", "run", "-d", "-p", "4000:4000", "--name", "my-express-app-container", "schema-generator-app:latest"
        );
        pb.inheritIO(); // shows output in console
        Process process = pb.start();
        process.waitFor();
    }

    public static void stopExpressContainer() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(
                "docker", "stop", "my-express-app-container"
        );
        pb.inheritIO();
        Process process = pb.start();
        process.waitFor();

        // Optional: remove container after stopping
        pb = new ProcessBuilder(
                "docker", "rm", "my-express-app-container"
        );
        pb.inheritIO();
        process = pb.start();
        process.waitFor();
    }

    public static void waitForExpress(String urlString) throws InterruptedException {
        boolean up = false;
        while (!up) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(2000);
                conn.setReadTimeout(2000);
                conn.setRequestMethod("GET");
                int code = conn.getResponseCode();
                if (code >= 200 && code < 500) { // server is responding
                    up = true;
                }
            } catch (IOException e) {
                // server not ready yet
            }
            if (!up) Thread.sleep(1000); // wait 1 second before retry
        }
    }

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {

       /* for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }*/

        try{

            startExpressContainer();
            waitForExpress("http://localhost:4000/");
            IntelliJTheme.setup( Main.class.getResourceAsStream("/arc-theme-orange.theme.json"));
            //IntelliJTheme.setup( Main.class.getResourceAsStream("/data/themes/arc-theme-orange.theme.json"));
        }catch (Exception e){
            e.printStackTrace();
        }
        // luching desktop app
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Color botton_color = new Color(245,121,0);
                final Home home;
                try {
                    home = new Home();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                home.setTitle("E2E-LOADER");
                ImageIcon folderIcon = new ImageIcon(getClass().getResource("/img/folder-25.png"));
                home.getPathJLabel().setText("");
                home.getPathJLabel().setIcon(folderIcon);
                home.getPathJButton().setText("");
                home.getPathJButton().setIcon(new ImageIcon(getClass().getResource("/img/search-25.png")));
                home.getPathJButton().setBackground(botton_color);
                home.getPathJButton().addActionListener(HomeServices.actionPathSearch(home));
                home.getSaveButton().addActionListener(HomeServices.actionSaveButton(home));
                home.getScriptChosenTable().setModel(new WorkloadTableModel());
                WorkloadTableService.setActionWorkloadTable(home.getScriptChosenTable(),home);
                E2eTableService.setActionE2ETable(home.getE2eTestCases(),home);
                home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                home.setSize(1000, 800);
                home.setLocationRelativeTo(null);
                ChartWorkload chartWorkload = new ChartWorkload();
                ChartPanel chartPanel = chartWorkload.ReturnChartPanel(((WorkloadTableModel)home.getScriptChosenTable().getModel()).getThredGroups());
                home.getCHART().add(chartPanel);
                home.setVisible(true);
                ImageIcon img = new ImageIcon(getClass().getResource("/img/logo.png"));
                home.setIconImage(img.getImage());
                home.addWindowListener(
                        new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                // Show a dialog before shutting down
                                JOptionPane.showMessageDialog(home,
                                        "The application is closing.\nPlease wait a moment while resources are released...",
                                        "Closing",
                                        JOptionPane.WARNING_MESSAGE);

                                // Stop container (blocking operation)
                                try {
                                    stopExpressContainer();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                                // Exit once cleanup is done
                                System.exit(0);
                            }
                        });
            }

        });
        /*
        // app exit, stop container
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                stopExpressContainer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));*/
    }
}
