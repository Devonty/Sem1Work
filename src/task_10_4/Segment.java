package task_10_4;

public class Segment {
    public final float start;
    public final float end;

    public Segment(float a, float b) {
        float start = Math.min(a, b);
        float end = Math.max(a, b);

        this.start = start;
        this.end = end;
    }

    public Segment(Segment other) {
        this(other.start, other.end);
    }

    public float length() {
        return end - start;
    }

    public float lengthTogether(Segment other) {
        Segment left = this.start > other.start ? other : this;
        Segment right = this.start > other.start ? this : other;
        // not collide
        if (left.end <= right.start)
            return left.length() + right.length();
        // collide
        return Math.max(left.end, right.end) - left.start;
    }

    @Override
    public String toString() {
        return start + " " + end;
    }

    public static void main(String[] args) {
        Segment a = new Segment(0, 5);
        Segment b = new Segment(0, 9);
        System.out.println(a.lengthTogether(b));
        System.out.println(b.lengthTogether(a));

    }
}
