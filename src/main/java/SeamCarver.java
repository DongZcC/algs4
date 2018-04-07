import edu.princeton.cs.algs4.Picture;

/**
 * 功能说明: week8 图像伸缩<br>
 * 有向图
 * 系统版本: v1.0<br>
 * 开发人员: @author dongzc15247<br>
 * 开发时间: 2018-04-06<br>
 */
public class SeamCarver {

    // 构造新的图片对象，不要改变入参中的引用
    private int[][] picture;
    private double[][] energy;
    private int width;
    private int height;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException("call method with none value");

        this.width = picture.width();
        this.height = picture.height();

        this.picture = new int[width][height];
        energy = new double[width][height];

        // 初始化picture数组
        for (int i = 0; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                this.picture[i][j] = picture.getRGB(i, j);
            }
        }

        // 初始化energy数组
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                energy[i][j] = computeEnergy(i, j);
            }
        }
    }

    // current picture
    public Picture picture() {
        Picture picture = new Picture(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                picture.setRGB(i, j, this.picture[i][j]);
            }
        }
        return picture;
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    // border pixels have energy 1000
    public double energy(int x, int y) {
        if (x < 0 || x >= width() || y < 0 || y >= height())
            throw new IllegalArgumentException("The pixel out of range");
        return energy[x][y];
    }


    private double computeEnergy(int x, int y) {
        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1)
            return 1000;
        return Math.sqrt(computeXGradientEnergy(x, y) + computeYGradientEnergy(x, y));
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        transpose();
        int[] result = findVerticalSeam();
        transpose();
        return result;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int seam[] = new int[height];
        double disTo[][] = new double[width][height];
        int edgeTo[][] = new int[width][height];

        // 初始化距离为无穷
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // 最底边能量为1000
                if (j == 0)
                    disTo[i][j] = energy[i][j];
                else
                    disTo[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        // 这样之后，distTo中的值已经是一个正确的值了, edgeTo中也放好了位置
        for (int j = 0; j < height - 1; j++) {
            for (int i = 0; i < width; i++) {
                relaxVertical(disTo, edgeTo, i, j);
            }
        }

        // 最终的距离得到
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < width; i++) {
            if (Double.compare(disTo[i][height - 1], min) < 0) {
                seam[height - 1] = i;
                min = disTo[i][height - 1];
            }
        }

        // 构造结果数组
        for (int j = height - 2; j >= 0; j--) {
            seam[j] = edgeTo[seam[j + 1]][j + 1];
        }
        return seam;
    }

    private void relaxVertical(double[][] disTo, int[][] edgeTo, int x, int y) {
        // 最优先比较顶头的那个
        if (disTo[x][y + 1] > disTo[x][y] + energy[x][y + 1]) {
            disTo[x][y + 1] = disTo[x][y] + energy[x][y + 1];
            edgeTo[x][y + 1] = x;
        }
        if ((x > 0) && (disTo[x - 1][y + 1] > disTo[x][y] + energy[x - 1][y + 1])) {
            disTo[x - 1][y + 1] = disTo[x][y] + energy[x - 1][y + 1];
            edgeTo[x - 1][y + 1] = x;
        }
        if ((x < width - 1) && (disTo[x + 1][y + 1] > disTo[x][y] + energy[x + 1][y + 1])) {
            disTo[x + 1][y + 1] = disTo[x][y] + energy[x + 1][y + 1];
            edgeTo[x + 1][y + 1] = x;
        }
    }


    // 矩阵置换
    private void transpose() {
        int temp = width;
        width = height;
        height = temp;

        double[][] transEnergy = new double[width][height];
        int[][] transPic = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                transEnergy[i][j] = energy[j][i];
                transPic[i][j] = picture[j][i];
            }
        }

        energy = transEnergy;
        picture = transPic;
    }

    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != width())
            throw new IllegalArgumentException("call method with none value");
        for (int i : seam) {
            if (i < 0 || i >= height())
                throw new IllegalArgumentException("The pixel out of range");
        }

        for (int i = 0; i < width; i++) {
            System.arraycopy(energy[i], seam[i] + 1, energy[i], seam[i], energy[i].length - seam[i] - 1);
            System.arraycopy(picture[i], seam[i] + 1, picture[i], seam[i], picture[i].length - seam[i] - 1);
        }

        this.height--;
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                System.out.printf("%9.2f ,", energy[x][y]);
//            }
//            System.out.println();
//        }
//        System.out.println("--------");
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || seam.length != height())
            throw new IllegalArgumentException("call method with none value");
        for (int i : seam) {
            if (i < 0 || i >= width())
                throw new IllegalArgumentException("The pixel out of range");
        }
        transpose();
        removeHorizontalSeam(seam);
        transpose();
    }

    private double computeXGradientEnergy(int x, int y) {
        int rgb = picture[x + 1][y];
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb) & 0xFF;

        int rgb2 = picture[x - 1][y];
        int r2 = (rgb2 >> 16) & 0xFF;
        int g2 = (rgb2 >> 8) & 0xFF;
        int b2 = (rgb2) & 0xFF;

        return Math.pow(r - r2, 2) + Math.pow(g - g2, 2) + Math.pow(b - b2, 2);
    }

    private double computeYGradientEnergy(int x, int y) {
        int rgb = picture[x][y - 1];
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb) & 0xFF;

        int rgb2 = picture[x][y + 1];
        int r2 = (rgb2 >> 16) & 0xFF;
        int g2 = (rgb2 >> 8) & 0xFF;
        int b2 = (rgb2) & 0xFF;

        return Math.pow(r - r2, 2) + Math.pow(g - g2, 2) + Math.pow(b - b2, 2);
    }
}
