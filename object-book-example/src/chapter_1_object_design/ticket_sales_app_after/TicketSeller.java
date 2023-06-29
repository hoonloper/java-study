package chapter_1_object_design.ticket_sales_app_after;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    // public TicketOffice getTicketOffice() {
    //    return ticketOffice;
    // }

    // Theater enter() 메서드 코드를 sellTo라는 메서드로 옮겨 자율성을 높임
    // 즉 개념적이나 물리적으로 객체 내부의 세부적인 사항을 감추는 것을 캡슐화라고 부름
    // 캡슐화의 목적은 변경하기 쉬운 객체를 만드는 것임
    // 캡슐화를 통해 객체 내부로의 접근을 제한하면 객체와 객체 사이의 결합도를 낮출 수 있기 때문에 설계를 좀 더 쉽게 변경 가능
    public void sellTo(Audience audience) {
        if(audience.getBag().hasInvitation()) {
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketOffice.plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
