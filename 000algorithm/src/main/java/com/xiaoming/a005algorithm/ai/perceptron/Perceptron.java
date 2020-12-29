package com.xiaoming.a005algorithm.ai.perceptron;

//感知器
public class Perceptron {
    //学习率
    private float learnRate;
    //学习次数
    private int studyCount;
    //阈值
    private float e;
    //权值,因为判断整数正负只需要一条输入，所以这里只有一个权值，多条输入可以设置为数组
    private float w;
    //每次学习的正确率
    private float[] correctRate;

    public Perceptron(float learnRate, int studyCount) {
        this.learnRate = learnRate;
        this.studyCount = studyCount;
        this.e = 0;
        this.w = 0;
        this.correctRate = new float[studyCount];
    }

    /**
     * 学习函数，samples 是一个包含输入数据和分类结果的二维数组，
     * samples[][0] 表示输入数据
     * samples[][1] 表示正确的分类结果
     *
     * @param samples 训练数据
     */
    public void fit(int[][] samples) {
        int sampleLength = samples.length;
        for (int i = 0; i < studyCount; i++) {
            int errorCount = 0;
            for (int[] sample : samples) {
                float update = learnRate * (sample[1] - predict(sample[0]));
                //更新权值、阈值
                w += update * sample[0];
                e += update;
                //计算错误次数
                if (update != 0) {
                    errorCount++;
                }
            }

            //计算此次学习的正确率
            correctRate[i] = 1 - errorCount * 1.0f / sampleLength;
        }
    }

    /**
     * 求和函数，模拟求和结点操作 输入数据 * 权值
     * @param num 输入数据
     * @return 求和结果 z
     */
    private float sum(int num) {
        return num * w + e;
    }

    /**
     * 激活函数，通过求和结果 z 和阈值 e 进行判断
     * @param num 输入数据
     * @return 分类结果
     */
    public int predict(int num) {
        return sum(num) >= 0 ? 1 : -1;
    }

    /**
     * 打印正确率
     */
    public void printCorrectRate() {
        for(int i = 0; i < studyCount; i++) {
            System.out.printf("bright#第%d次学习的正确率 -> %.2f%%\n",i+1, correctRate[i] * 100);
        }
    }
}
