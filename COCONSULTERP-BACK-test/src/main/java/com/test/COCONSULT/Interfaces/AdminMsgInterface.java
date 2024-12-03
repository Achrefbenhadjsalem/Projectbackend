package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.Entity.AdminMsg;

import java.util.List;

public interface AdminMsgInterface {
    void createmsg (AdminMsg adminMsg);
    void deletemsg (Long id);
    AdminMsg getmsg (Long id);
    List <AdminMsg> getallmsg ();
    void sendmsg (Long idadminMsg, List<Long> idusers);
}
