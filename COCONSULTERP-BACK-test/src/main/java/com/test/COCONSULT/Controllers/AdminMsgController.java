package com.test.COCONSULT.Controllers;
import com.test.COCONSULT.Entity.AdminMsg;

import com.test.COCONSULT.ServiceIMP.AdminMsgServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Msg")
public class AdminMsgController {

    @Autowired
    private AdminMsgServiceIMP adminMsgServiceIMP;

    @PostMapping("/create")
    public void createMsg(@RequestBody AdminMsg adminMsg) {
        adminMsgServiceIMP.createmsg(adminMsg);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMsg(@PathVariable Long id) {
        adminMsgServiceIMP.deletemsg(id);
    }

    @GetMapping("/get/{id}")
    public AdminMsg getMsg(@PathVariable Long id) {
        return adminMsgServiceIMP.getmsg(id);
    }

    @GetMapping("/getAll")
    public List<AdminMsg> getAllMsg() {
        return adminMsgServiceIMP.getallmsg();
    }

    @PostMapping("/send")
    public void sendMsg(@RequestParam Long adminMsgId, @RequestParam List<Long> idUsers) {
        adminMsgServiceIMP.sendmsg(adminMsgId, idUsers);
    }

    @PostMapping("/sendNotification/{adminMsgId}/{Title}/{message}/{recipients}")
    public void sendNotification(@PathVariable ("adminMsgId") Long adminMsgId,@PathVariable  ("Title") String Title,@PathVariable ("message") String message, @PathVariable ("recipients") List<String> recipients) {
        adminMsgServiceIMP.sendNotification(adminMsgId, Title,message, recipients);
    }
}
