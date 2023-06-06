package kephispring.helloboot;

public interface HelloRepository {
    Hello findHello(String name);

    void increaseCount(String name);

    // default 사용을 분석하고 싶다면 Comparator 까보기
    default int countOf(String name) {
        Hello hello = findHello(name);
        return hello == null ? 0 : hello.getCount();
    }
}
