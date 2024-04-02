//游戏实体类
public class GameEnity {
    //生物类
    public class Organism {
        //名字
        private String name;
        //编号
        private int id;
        //生命值
        private int HP;
        //攻击力
        private int ATK;
        //防御力
        private int DEF;
        //暴击率
        private int CRT;
        //暴击伤害加成
        private int CDB;
        //减伤率
        private int DRR;
        //防御加成
        private int DFB;

        Organism() {
        }

        //构造方法
        public Organism(String name, int id, int HP, int ATK, int DEF, int CRT, int CDB, int DRR, int DFB) {
            this.name = name;
            this.id = id;
            this.HP = HP;
            this.ATK = ATK;
            this.DEF = DEF;
            this.CRT = CRT;
            this.CDB = CDB;
            this.DRR = DRR;
            this.DFB = DFB;
        }

        //获取名字
        public String getName() {
            return name;
        }

        //获取编号
        public int getId() {
            return id;
        }

        //获取生命值
        public int getHP() {
            return HP;
        }

        //修改生命值
        public void setHP(int HP) {
            this.HP = HP;
        }

        //获取攻击力
        public int getATK() {
            return ATK;
        }

        //获取防御力
        public int getDEF() {
            return DEF;
        }

        //获取暴击率
        public int getCRT() {
            return CRT;
        }

        //获取暴伤加成
        public int getCDB() {
            return CDB;
        }

        //获取减伤率
        public int getDRR() {
            return DRR;
        }

        //获取防御加成
        public int getDFB() {
            return DFB;
        }
    }

    //玩家类
    public class Player extends Organism {
        //经验值
        private int EXP;
        //等级
        private int LV;

        //构造方法
        public Player(String name, int id, int HP, int ATK, int DEF, int EXP, int LV, int CRT, int CDB, int DRR, int DFB) {
            //父类构造方法
            super(name, id, HP, ATK, DEF, CRT, CDB, DRR, DFB);
            this.EXP = EXP;
            this.LV = LV;
        }

        //获取经验值
        public int getEXP() {
            return EXP;
        }

        //修改经验值
        public void setEXP(int EXP) {
            this.EXP = EXP;
        }

        //获取等级
        public int getLV() {
            return LV;
        }
    }

    //怪物类
    public class Monster extends Organism {
        //构造方法
        public Monster(String name, int id, int HP, int ATK, int DEF, int CRT, int CDB, int DRR, int DFB) {
            //父类构造方法
            super(name, id, HP, ATK, DEF, CRT, CDB, DRR, DFB);
        }
    }

    //奖品类
    public class Prize {
        //名字
        private String name;
        //概率
        private int probability;

        Prize() {
        }

        //构造方法
        Prize(String name, int probability) {
            this.name = name;
            this.probability = probability;
        }

        //获取名字
        public String getName() {
            return name;
        }

        //获取概率
        public int getProbability() {
            return probability;
        }
    }
}
