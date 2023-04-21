/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author DELL-PC
 */
import Tienich.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {

    private List<Object[]>getListOfArray(String sql, String[] cols,Object...args){
        int i;
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(i = 0; i < cols.length; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
             return list;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
       
    }
    public List<Object[]> getLuongNguoiHoc() {
      String sql = "{Call sp_LuongNguoiHoc}";
      String[] cols ={"Nam","SoLuong","DauTien","CuoiCung"};
      return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getBangDiem(Integer makh) {
          String sql ="{CALL sp_LuongNguoiHoc}";
       String[] cols = {"MaNH","HoTen","Diem"};
       return this.getListOfArray(sql, cols, makh);
    }

    public List<Object[]> getDiemTheoChuyenDe() {
        String sql = "{CALL sp_DiemChuyenDe}";
        String[] cols ={"ChuyenDe","SoHV","ThapNhat","CaoNhat","TrungBinh"};
        return this.getListOfArray(sql, cols);
    }

    public List<Object[]> getDoanhThu(int nam) {
        String sql = "{CALL sp_DoanhThu(?)}";
        String[] cols = {"ChuyenDe","SoKH","SoHV","DoanhThu","ThapNhat","CaoNhat","TrungBinh"};
        return this.getListOfArray(sql, cols, nam);
    }
}
