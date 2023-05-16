package uhl_programing.VL3;

import javax.net.ssl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class DownloadWithProgressNIOUI {
    public static void main(String[] args) throws Exception {
        disableSSLVerification();
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Datei-Downloader");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            JButton downloadButton = new JButton("Download");
            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);

            downloadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Thread(() -> {
                        downloadButton.setEnabled(false);
                        downloadFileWithProgressNIO("https://speed.hetzner.de/100MB.bin", "big.file", progressBar);
                        downloadButton.setEnabled(true);
                    }).start();
                }
            });

            frame.add(downloadButton);
            frame.add(progressBar);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void downloadFileWithProgressNIO(String fileURL, String savePath, JProgressBar progressBar) {
        try {
            final long timeStart = System.currentTimeMillis();
            URL url = new URL(fileURL);
            InputStream inStream = url.openStream();
            ReadableByteChannel inChannel = Channels.newChannel(inStream);
            FileOutputStream outStream = new FileOutputStream(savePath);
            FileChannel outChannel = outStream.getChannel();
            long fileSize = url.openConnection().getContentLengthLong();
            progressBar.setMaximum((int) fileSize);
            final long[] loaded = new long[1];

            while (loaded[0] < fileSize) {
                long transferred = outChannel.transferFrom(inChannel, loaded[0], 5000);
                loaded[0] += transferred;
                //System.out.println("Loaded " + loaded[0] + " from " + fileSize + ".");
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        progressBar.setValue((int) loaded[0]);
                    }
                });
            }

            inStream.close();
            inChannel.close();
            outChannel.close();
            outStream.close();

            final long timeEnd = System.currentTimeMillis();
            System.out.println("Verlaufszeit der Schleife: " + (timeEnd - timeStart) + " Millisek.");
            JOptionPane.showMessageDialog(null, "The file was succesfully downloaded");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while downloading the requested file");
        }
    }

    private static void disableSSLVerification() throws Exception {
        // Create a trust manager that accepts all certificates
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Activate the custom trust manager
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
    }
}


