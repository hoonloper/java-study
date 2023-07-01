package chapter_4_design_quality_and_tradeoffs.encapsulation_example;

public class Rectangle {
    private int left;
    private int top;
    private int right;
    private int bottom;

    public Rectangle(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    // 현재 Rectangle 클래스는 코드 중복이 발생할 확률이 높고, 변경에 취약하다.
    public class AnyClass {
        void anyMethod(Rectangle rectangle, int multiple) {
            rectangle.setRight(rectangle.getRight() * multiple);
            rectangle.setBottom(rectangle.getBottom() * multiple);
            // ...
        }
    }

    // enlarge 메서드를 구현함으로서 위와 같은 문제를 해결할 수 있다.
    public void enlarge(int multiple) {
        right *= multiple;
        bottom *= multiple;
    }
}
