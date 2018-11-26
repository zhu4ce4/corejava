class EnemyHeroDeadException extends Exception {

    public EnemyHeroDeadException() {

    }

    public EnemyHeroDeadException(String msg) {
        super(msg);
    }
}


class Hero {
    public String name;
    protected float hp;

    public Hero(String aname, float startHp) {
        name = aname;
        hp = startHp;
    }

    public void attackHero(Hero hero) throws EnemyHeroDeadException {
        if (hero.hp == 0) {
            throw new EnemyHeroDeadException(hero.name + "已经挂了");
        }
        hero.hp--;
    }

    public String toString() {
        return name;
    }

//    public static void main(String[] args) {
//        Hero gareen = new Hero("盖伦", 918);
//        Hero teemo = new Hero("提莫", 0);
//
//        try {
//            gareen.attackHero(teemo);
//        } catch (EnemyHeroDeadException e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
}


class IndexIsNagetiveException extends Exception {
}

class IndexOutOfRangeException extends Exception {

}

interface IStringBuffer {
    public void append(String str) throws IndexOutOfRangeException, IndexIsNagetiveException;

    public void append(char c) throws IndexOutOfRangeException, IndexIsNagetiveException;

    public void insert(int pos, char b) throws IndexOutOfRangeException, IndexIsNagetiveException;

    public void insert(int pos, String b) throws IndexOutOfRangeException, IndexIsNagetiveException;

    public void delete(int start) throws IndexOutOfRangeException, IndexIsNagetiveException;

    public void delete(int start, int end) throws IndexOutOfRangeException, IndexIsNagetiveException;

    public void reverse();

    public int length();
}

public class MyStringBuffer implements IStringBuffer {
    int capacity = 16;
    int length = 0;
    char[] value;

    public MyStringBuffer() {
        value = new char[capacity];
    }

    public MyStringBuffer(String str) {
        this();
        if (null == str) {
            return;
        }
        if (capacity < str.length()) {
            capacity = value.length * 2;
            value = new char[capacity];
        }
        if (capacity >= str.length()) {
            System.arraycopy(str.toCharArray(), 0, value, 0, str.length());
        }
        length = str.length();
    }

    @Override
    public void append(String str) throws IndexOutOfRangeException, IndexIsNagetiveException {
        insert(length, str);
    }

    @Override
    public void append(char c) throws IndexOutOfRangeException, IndexIsNagetiveException {
        append(String.valueOf(c));
    }

    @Override
    public void insert(int pos, char b) throws IndexOutOfRangeException, IndexIsNagetiveException {
        insert(pos, String.valueOf(b));
    }

    @Override
    public void insert(int pos, String b) throws IndexOutOfRangeException, IndexIsNagetiveException {
        if (pos < 0) {
            throw new IndexIsNagetiveException();
        }
        if (pos > length) {
            throw new IndexOutOfRangeException();
        }
        if (null == b) {
            throw new NullPointerException();
        }
        if (length + b.length() > capacity) {
            capacity = (int) ((length + b.length()) * 2.5f);
            char[] newValue = new char[capacity];
            System.arraycopy(value, 0, newValue, 0, length);
            value = newValue;
        }
        char[] cs = b.toCharArray();
        System.arraycopy(value, pos, value, pos + cs.length, length - pos);
        System.arraycopy(cs, 0, value, pos, cs.length);
        length += cs.length;
    }

    @Override
    public void delete(int start) throws IndexOutOfRangeException, IndexIsNagetiveException {
        delete(start, length);
    }

    @Override
    public void delete(int start, int end) throws IndexOutOfRangeException, IndexIsNagetiveException {
        if (start < 0) {
            throw new IndexIsNagetiveException();
        }
        if (start > length) {
            throw new IndexOutOfRangeException();
        }
        if (end < 0) {
            throw new IndexIsNagetiveException();
        }
        if (end > length) {
            throw new IndexOutOfRangeException();
        }
        if (start >= end) {
            throw new IndexOutOfRangeException();
        }

        System.arraycopy(value, end, value, start, length - end);
        length -= end - start;
    }

    @Override
    public void reverse() {
        for (int i = 0; i < length / 2; i++) {
            char temp = value[i];
            value[i] = value[length - i - 1];
            value[length - i - 1] = temp;
        }
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public String toString() {
        char[] realValue = new char[length];
        System.arraycopy(value, 0, realValue, 0, length);
        return new String(realValue);
    }

    public static void main(String[] args) {
        MyStringBuffer msb = new MyStringBuffer("thert99");
        for (char c : msb.value) {
            System.out.print(c);
        }
        System.out.println();
        System.out.println(msb.length);
        try {
            msb.delete(1, 3);
            for (char c : msb.value) {
                System.out.print(c);
            }
            System.out.println();
            System.out.println(msb);
            System.out.println(msb.length);
        } catch (IndexOutOfRangeException e) {
            e.printStackTrace();
        } catch (IndexIsNagetiveException e) {
            e.printStackTrace();
        }
    }
}
