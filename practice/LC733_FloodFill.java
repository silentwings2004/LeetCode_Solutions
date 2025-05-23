package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: FloodFill
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 733. Flood Fill
 */
public class LC733_FloodFill {
    /**
     * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from
     * 0 to 65535).
     *
     * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value
     * newColor, "flood fill" the image.
     *
     * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting
     * pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also
     * with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with
     * the newColor.
     *
     * At the end, return the modified image.
     *
     * Example 1:
     * Input:
     * image = [[1,1,1],[1,1,0],[1,0,1]]
     * sr = 1, sc = 1, newColor = 2
     * Output: [[2,2,2],[2,2,0],[2,0,1]]
     * Explanation:
     * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
     * by a path of the same color as the starting pixel are colored with the new color.
     * Note the bottom corner is not colored 2, because it is not 4-directionally connected
     * to the starting pixel.
     * Note:
     *
     * The length of image and image[0] will be in the range [1, 50].
     * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
     * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    // S1: BFS
    // time = O(m * n), space = O(m * n)
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // corner case
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
            return null;
        }
        int row = image.length, col = image[0].length;
        int color = image[sr][sc];
        boolean[][] visited = new boolean[row][col];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(sr * col + sc);
        image[sr][sc] = newColor;
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / col, j = cur % col;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < row && jj >= 0 && jj < col && image[ii][jj] == color && !visited[ii][jj]) {
                        queue.offer(ii * col + jj);
                        visited[ii][jj] = true;
                        image[ii][jj] = newColor;
                    }
                }
            }
        }
        return image;
    }
}
