package controller;

import model.dao.AdminGameDao;
import model.dto.CharacterDto;

import java.util.ArrayList;

public class AdminGameController {
    private static AdminGameController adminGameController = new AdminGameController();
    private AdminGameController(){}
    public static AdminGameController getInstance(){
        return adminGameController;
    }

    // 캐릭터 전체 출력
    public ArrayList<CharacterDto> characterAllPrint(){
        return AdminGameDao.getInstance().characterAllPrint();
    }   // characterAllPrint() end
}
