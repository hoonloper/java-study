package chapter_1_object_design.ticket_sales_app_after;

public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    // public Bag getBag() {
    //     return bag;
    // }

    // getBag 메서드 제거와 함께 Bag의 존재를 내부로 캡슐화
    // 즉 TicketSeller와 Audience 사이의 결합도가 낮아짐
    public long buy(Ticket ticket) {
        return bag.hold(ticket);
    }
}
