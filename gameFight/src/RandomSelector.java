public class RandomSelector {
    //获取随机数
    public int GetRandomNum(int num1, int num2) {
        //界定随机数范围为num1至num2
        int result = (int) (num1 + Math.random() * (num2 - num1 + 1));
        return result;
    }

    //获取选择结果
    public int GetSelectResult(int[] array) {
        //num为0~100之间的随机数
        int num = GetRandomNum(0, 100);
        //将数组中的概率数值逐渐加到sum里，找到num所在的概率区间，返回该区间的下标(sum最大值为100)
        for (int i = 0, sum = 0; i < array.length; i++) {
            sum += array[i];
            if (num >= sum - array[i] && num <= sum)
                return i;
        }
        //如果上面的循环没有返回，就返回-1，代表“没抽中”或者“异常”
        return -1;
    }
}
