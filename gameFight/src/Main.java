import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    // 回合数
    public static int roundNum = 0;

    // 回合
    public static void Round(GameEnity.Organism[] organisms) {
        // 随机选择器
        RandomSelector rdSelector = new RandomSelector();
        // 攻击者编号
        int attackerId = -1;
        // 受击者编号
        int victimId = -1;
        // 随机选出攻击者，A和B的概率都是25%，M的概率是50%
        int[] attackerArray = { 25, 25, 50 };
        // 攻击者
        attackerId = rdSelector.GetSelectResult(attackerArray);
        // 如果攻击者是玩家A或B，受击者就是怪物M
        if (attackerId == 0 || attackerId == 1) {
            // 受击者
            victimId = 2;
        }
        // 如果攻击者是怪物，就要从玩家A和B中随机选一个作为受击者
        else if (attackerId == 2) {
            // 随机选出受击者，A和B的概率都是50%
            int[] victimArray = { 50, 50 };
            // 受击者
            victimId = rdSelector.GetSelectResult(victimArray);
        }
        // 如果随机选择结果返回异常，则跳过当前回合
        if (attackerId == -1 || victimId == -1)
            return;
        // 如果攻击者或者受击者已经死亡，则跳过当前回合
        if (organisms[attackerId].getHP() <= 0 || organisms[victimId].getHP() <= 0)
            return;
        roundNum++;
        System.out.println("*************************" + "第" + roundNum + "回合" + "*************************");
        System.out.println(organisms[attackerId].getName() + "对" + organisms[victimId].getName() + "发动攻击!");
        // 攻击
        int att = organisms[attackerId].getATK();
        // 防御
        int def = organisms[victimId].getDEF();
        // 攻击者暴击率
        int[] crtArray = { organisms[attackerId].getCRT() };
        // 受击者减伤率
        int[] drrArray = { organisms[victimId].getDRR() };
        // 如果触发暴击，伤害增加
        if (rdSelector.GetSelectResult(crtArray) != -1) {
            System.out.print(organisms[attackerId].getName() + "触发暴击，攻击力由" + att + "提升至");
            att += att * organisms[attackerId].getCDB() / 100;
            System.out.println(att + "!");
        }
        // 如果触发减伤，防御增加
        if (rdSelector.GetSelectResult(drrArray) != -1) {
            System.out.print(organisms[victimId].getName() + "触发减伤，防御力由" + def + "提升至");
            def += def * organisms[victimId].getDFB() / 100;
            System.out.println(def + "!");
        }
        // 伤害
        int dmg = att - def;
        System.out.println("造成伤害：" + dmg);
        // 受击者生命值减少
        System.out.print(organisms[victimId].getName() + "的生命值由" + organisms[victimId].getHP() + "降至");
        organisms[victimId].setHP(organisms[victimId].getHP() - dmg);
        // 如果生命值低于0，则修改生命值为0
        if (organisms[victimId].getHP() < 0)
            organisms[victimId].setHP(0);
        System.out.println(organisms[victimId].getHP() + "!");
        System.out.println("各生物当前生命值：");
        for (int i = 0; i < 3; i++) {
            System.out.println(organisms[i].getName() + "：" + organisms[i].getHP());
        }
    }

    // 奖励
    public static void Reward(GameEnity.Prize[] rwd_a, GameEnity.Prize[] rwd_b, int Lv) {
        // 随机选择器
        RandomSelector rdSelector = new RandomSelector();
        // 奖励类型
        int[] rwdArray = { 40, 60 };
        int rwd = rdSelector.GetSelectResult(rwdArray);

        // 奖品
        int[] prizeArray;
        // rwd-a
        if (rwd == 0) {
            prizeArray = new int[rwd_a.length];
            for (int i = 0; i < rwd_a.length; i++) {
                prizeArray[i] = rwd_a[i].getProbability();
            }
            System.out.println(rwd_a[rdSelector.GetSelectResult(prizeArray)].getName() + "!");
            System.out.println("获得经验:" + Lv * 200);
        }
        // rwd-b
        else if (rwd == 1) {
            prizeArray = new int[rwd_b.length];
            for (int i = 0; i < rwd_b.length; i++) {
                prizeArray[i] = rwd_b[i].getProbability();
            }
            System.out.println(rwd_b[rdSelector.GetSelectResult(prizeArray)].getName() + "!");
            System.out.println("获得经验:" + Lv * 100);
        }
    }

    // 战斗
    public static void Fight() {
        // 初始化角色和怪物
        GameEnity e = new GameEnity();
        GameEnity.Organism[] organisms = new GameEnity.Organism[3];
        organisms[0] = e.new Player("A", 0, 1000, 180, 20, 1000, 10, 40, 50, 40, 10);
        organisms[1] = e.new Player("B", 1, 1100, 200, 30, 2000, 15, 50, 30, 60, 15);
        organisms[2] = e.new Monster("M", 2, 2000, 150, 50, 40, 50, 20, 10);
        // 初始化奖品
        GameEnity.Prize[] rwd_a = new GameEnity.Prize[5];
        rwd_a[0] = e.new Prize("金刚石", 20);
        rwd_a[1] = e.new Prize("红宝石", 20);
        rwd_a[2] = e.new Prize("乾坤袋", 10);
        rwd_a[3] = e.new Prize("如意丹", 20);
        rwd_a[4] = e.new Prize("金柳露", 30);
        GameEnity.Prize[] rwd_b = new GameEnity.Prize[6];
        rwd_b[0] = e.new Prize("净瓶甘露", 10);
        rwd_b[1] = e.new Prize("人参果", 10);
        rwd_b[2] = e.new Prize("藏宝图", 30);
        rwd_b[3] = e.new Prize("黑宝石", 20);
        rwd_b[4] = e.new Prize("上古灵符", 15);
        rwd_b[5] = e.new Prize("避水珠", 15);

        // 循环战斗（直至胜利或失败）
        while (organisms[2].getHP() > 0 && (organisms[0].getHP() > 0 || organisms[1].getHP() > 0)) {
            // 回合
            Round(organisms);
        }

        // 战斗结束
        if (organisms[2].getHP() == 0) {
            System.out.println("*************************战斗胜利*************************");
            // 奖励
            GameEnity.Player pa = (GameEnity.Player)organisms[0];
            GameEnity.Player pb = (GameEnity.Player)organisms[1];
            System.out.print("角色A获得");
            Reward(rwd_a, rwd_b, pa.getLV());
            System.out.print("角色B获得");
            Reward(rwd_a, rwd_b, pb.getLV());
        } else {
            System.out.println("*************************战斗失败*************************");
        }
    }

    // 游戏界面
    public static void GameInterface() {
        // 界面
        Interface Itf = new Interface();
        // 窗口实例（JFrame）
        Interface.Window window = Itf.new Window();
        // 按钮实例（JButton）
        JButton button = window.main();
        // 按钮事件监听
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 按钮被点击，子循环结束
                window.key = 1;
            }
        });
        // 循环(反复点击“PLAY”按钮，可以反复战斗)
        while (true) {
            System.out.print(' ');
            System.out.print("\b \b");
            // 检测开始按钮有无被点击
            if (window.key == 1) {
                window.key = 0;
                // 战斗
                Fight();
                // 回合数清零
                roundNum = 0;
            }
        }
    }

    // 主方法
    public static void main(String[] args) {
        // 游戏界面
        GameInterface();
    }
}