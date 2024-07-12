//package SimpleClock;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements Runnable, ActionListener {
    Date dateObj = new Date();

    static volatile boolean conditional = true;
    boolean hour24Format = false;
    boolean gmtFormat = false;

    //Josue
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;

//        SimpleDateFormat gmtTimeFormat = new SimpleDateFormat("HH:mm:ss");
//        gmtTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;
        //I coded below here.
        JButton button1224 = new JButton("Switch 12/24HR");
        JButton buttonTimeZone = new JButton("Switch Local/GMT");




        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(550, 250);
            this.setResizable(true);

            button1224.addActionListener(e -> {
                hour24Format = !hour24Format; // Toggle the format

                if (hour24Format) {
                    timeFormat = new SimpleDateFormat("kk:mm:ss a"); // 24-hour format
                } else {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a"); // 12-hour format
                }
            });

            if (hour24Format) {
                timeFormat = new SimpleDateFormat("kk:mm:ss a"); // 24-hour format
            } else {
                timeFormat = new SimpleDateFormat("hh:mm:ss a"); // 12-hour format
            }

            buttonTimeZone.addActionListener(e -> {
                gmtFormat = !gmtFormat;
                System.out.println(gmtFormat);


                if (gmtFormat) {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a");// 24-hour format
                    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    time = timeFormat.format(dateObj);
                }
                else {
                    timeFormat = new SimpleDateFormat("hh:mm:ss a");// 24-hour format
//                    timeFormat.setTimeZone(TimeZone.getTimeZone("EST"));
                    time = timeFormat.format(dateObj);
                }
            });

            if (gmtFormat) {
                timeFormat = new SimpleDateFormat("hh:mm:ss a");
                timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                time = timeFormat.format(dateObj);
            }
            else {
                timeFormat = new SimpleDateFormat("hh:mm:ss a");// 24-hour format
//                timeFormat.setTimeZone(TimeZone.getTimeZone("EST"));
                time = timeFormat.format(dateObj);
            }

            //testing gmt here
//            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
//            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//            System.out.println(sdf.format(dateObj));

            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            //border logic goes here
            Border border = dateLabel.getBorder();
            Border margin = new EmptyBorder(20,20,0,20);
            dateLabel.setBorder(new CompoundBorder(border,margin));
            Border border2 = dayLabel.getBorder();
            Border margin2 = new EmptyBorder(20,20,0,20);
            dayLabel.setBorder(new CompoundBorder(border2,margin2));
            Border border3 = timeLabel.getBorder();
            Border margin3 = new EmptyBorder(20,20,0,20);
            timeLabel.setBorder(new CompoundBorder(border3,margin3));
            //all this I added myself - as well as change size.

    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.add(button1224); //I added this! :)
            this.add(buttonTimeZone); //and this.
            this.setVisible(true);
    
            setTimer();
        }

        public void setTimer() {

                run();
//            while (true) {
//
//            }
        }



        public static void main(String[] args) {
//            new SimpleClock();
            SimpleClock clock = new SimpleClock();
            Thread thread = new Thread(clock);
            thread.start();



        }

    //this is from the Runnable interface
    @Override
    public void run() {



            do {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
//                System.out.println("Running...");
            } while (conditional);


    }

    //this is from the Actionlistener interface
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
