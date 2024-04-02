import javax.swing.*;
import java.net.URL;

//界面类
public class Interface {
    //窗口类
    public class Window extends JFrame {
        //循环结束条件
        public int key;
        //按钮
        private JButton startBt;
        //背景图片标签
        private JLabel bkImg;
        //按钮面板
        private JPanel gamePanel;

        //创建窗口
        public JButton CreateWindow() {
            key = 0;
            //定义面板容器
            gamePanel = new JPanel();
            //背景图片标签
            bkImg = new JLabel();
            //图片相对路径
            URL imageUrl1 = getClass().getClassLoader().getResource("image/bkImage.png");
            //标签图标实例
            ImageIcon imageIcon1 = new ImageIcon(imageUrl1);
            bkImg.setIcon(imageIcon1);
            bkImg.setBounds(0, 0, 760, 390);

            //图片相对路径
            URL imageUrl2 = getClass().getClassLoader().getResource("image/playgame.png");
            //标签图标实例
            ImageIcon imageIcon2 = new ImageIcon(imageUrl2);
            //按钮图片
            startBt = new JButton(imageIcon2);
            //设置按钮显示位置和大小
            startBt.setBounds(610, 241, 148, 148);
            //将按钮添加到面板容器中
            gamePanel.add(startBt);

            //窗口实例
            JFrame window = new JFrame();
            //面板
            window.setContentPane(gamePanel);
            //绝对位置
            window.getContentPane().setLayout(null);
            //背景图片
            window.getContentPane().add(bkImg);
            //窗口标题
            window.setTitle("游戏");
            //窗口位置和大小
            window.setBounds(600, 200, 774, 427);
            //窗口大小固定
            window.setResizable(false);
            //窗口基础属性
            window.setVisible(true);
            window.setDefaultCloseOperation(EXIT_ON_CLOSE);
            return startBt;
        }

        //主方法
        public JButton main() {
            //创建窗口（返回获取的JButton实例）
            return new Window().CreateWindow();
        }
    }
}
