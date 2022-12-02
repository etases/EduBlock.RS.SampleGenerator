package me.hsgamer.edublock.rs.samplegenerator.list;

import lombok.experimental.UtilityClass;
import me.hsgamer.edublock.rs.samplegenerator.data.AdminAccountCreateAndProfile;
import me.hsgamer.edublock.rs.samplegenerator.data.StaffAccountCreateAndProfile;
import me.hsgamer.edublock.rs.samplegenerator.data.StudentAccountCreateAndProfile;
import me.hsgamer.edublock.rs.samplegenerator.data.TeacherAccountCreateAndProfile;

import java.util.List;

@UtilityClass
public class DataList {
    public static final List<AdminAccountCreateAndProfile> adminList = List.of(
            new AdminAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Giang Hán Vĩnh",
                    true,
                    1999,
                    "2, Thôn Giang Hùng Trưởng, Xã Tú Lưu, Huyện Vũ Hoán Nương, Tuyên Quang",
                    "vu78@gmail.com"
            )),
            new AdminAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "La Nguyên",
                    false,
                    1998,
                    "43 Phố Giao, Xã 31, Quận Hữu, Đồng Tháp",
                    "kbanh@yahoo.com"
            ))
    );
    public static final List<StaffAccountCreateAndProfile> staffList = List.of(
            new StaffAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Châu Phương",
                    false,
                    1999,
                    "0782 Phố An Khuyên Đức, Xã Tài, Quận Thịnh, Hà Nội",
                    "vien03@nong.ac.vn"
            )),
            new StaffAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Tuấn Yên",
                    true,
                    1998,
                    "2 Phố Nữ, Phường Lợi Nương, Quận Trà, Sóc Trăng",
                    "lhang@moc.int.vn"
            ))
    );
    public static final List<TeacherAccountCreateAndProfile> teacherList = List.of(
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Việt Hoà",
                    true,
                    1999,
                    "951 Phố Cự, Phường Quản, Quận Tiêu Xuyến Mỹ, Thừa Thiên Huế",
                    "hthieu@bui.com.vn"
            ), 1),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Giang Hùng",
                    true,
                    1998,
                    "694, Thôn Văn Tuyết Nghiêm, Xã Lễ Đoàn, Huyện Dương, Hải Dương",
                    "ihang@lu.net"
            ), 2),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Hồng Hùng",
                    true,
                    1999,
                    "2, Thôn Giang Hùng Trưởng, Xã Tú Lưu, Huyện Vũ Hoán Nương, Tuyên Quang",
                    "hung@gmail.com"
            ), 3),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Khải Ngô",
                    false,
                    1998,
                    "9318 Phố Trác Đình Nương, Phường Thủy Vi, Quận Mi Thạc, Lạng Sơn",
                    "chung.chieu@yahoo.com"
            ), 4),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Kim Kim",
                    false,
                    1999,
                    "7851 Phố Hùng Phụng Tiên, Thôn Yên Phụng, Huyện Chế Mỹ Nguyên, Hồ Chí Minh",
                    "phuong.binh@gmail.com"
            ), 5),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Hằng Chà",
                    false,
                    1998,
                    "26, Thôn 75, Phường 4, Quận Vịnh Cù, Thừa Thiên Huế",
                    "pham.trach@khuat.biz"
            ), 6),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Trúc Thảo",
                    false,
                    1999,
                    "28, Thôn Hạ Liễu, Phường Vịnh Khưu, Huyện Lợi Đài, Kiên Giang",
                    "chinh.mach@hotmail.com"
            ), 7),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Trúc Đan",
                    false,
                    1997,
                    "6, Ấp Ngọc Cầm, Xã Minh Hiền, Quận 7, Phú Thọ",
                    "tong.yen@trang.com"
            ), 8),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Nhã Chà",
                    false,
                    1998,
                    "0230 Phố Khoa, Xã Kiệt Trương, Huyện Trình Thực, Hải Phòng",
                    "diep36@cu.edu.vn"
            ), 9),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Gái Diệp",
                    false,
                    1999,
                    "93, Ấp Hậu Bào, Phường Lễ Phó, Quận Hán, Bà Rịa - Vũng Tàu",
                    "rba@yahoo.com"
            ), 10),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Xuân Vân",
                    false,
                    1998,
                    "8752 Phố Phùng Khải Hùng, Xã Hồ Kiều Nga, Quận Lợi, Lào Cai",
                    "unghiem@dong.edu.vn"
            ), 11),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Duyên Nhã",
                    false,
                    1999,
                    "5, Ấp Vi Hải Phương, Phường Việt, Huyện Vỹ, Trà Vinh",
                    "luu.duc@ngo.info"
            ), 12),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Ngọc Lưu",
                    false,
                    1998,
                    "60 Phố Bùi Lâm Trầm, Xã 57, Quận Cao Đào Thư, Điện Biên",
                    "hung29@yahoo.com"
            ), 13),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Trường Nguyễn",
                    true,
                    1999,
                    "767 Phố Thi Khang Đôn, Phường Hưng Chương, Quận Tông Nhi Kiệt, Đà Nẵng",
                    "tiep59@yahoo.com"
            ), 14),
            new TeacherAccountCreateAndProfile(RandomInfo.getProfileUpdate(
                    "Lập Đoàn",
                    true,
                    1998,
                    "7 Phố Nông Hạ Thảo, Phường 45, Huyện Lễ Đoàn, Hà Nội",
                    "abui@thinh.org"
            ), 15)
    );
    public static final List<StudentAccountCreateAndProfile> studentList = List.of(
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Ma Đại",
                    true,
                    2001,
                    "04, Thôn Đổng Thắm Huyền, Xã Mạc Hữu Minh, Quận Tâm Thào, Yên Bái",
                    "bach.chuong@hotmail.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Mạc Nam Đoàn",
                    true,
                    2001,
                    "9, Ấp Ứng, Phường 5, Quận 7, Hà Nội",
                    "kto@hotmail.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Dư Bửu Thịnh",
                    true,
                    2001,
                    "1982, Ấp Hạ, Thôn Hà Yến, Huyện Hồng, Khánh Hòa",
                    "dinh.thuong@lu.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Lỡ Ái Nương",
                    false,
                    2001,
                    "54 Phố Lâm Thịnh Mỹ, Xã Chung Thái Phương, Quận 77, Cần Thơ",
                    "kdam@hotmail.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Lương Trạch",
                    true,
                    2001,
                    "93, Ấp Quảng Bằng, Phường Mộc, Huyện Khâu Công, Đắk Lắk",
                    "danh.giang@hotmail.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Cao Tài Minh",
                    true,
                    2001,
                    "914 Phố Cát, Xã Quách, Quận 8, Cần Thơ",
                    "can.khong@yahoo.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Quản Bội Hậu",
                    false,
                    2001,
                    "9850 Phố Vương, Phường 07, Huyện 3, Hải Phòng",
                    "yen78@hotmail.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Lò Giáng Khuê",
                    true,
                    2001,
                    "793 Phố Chưởng, Xã Uy, Huyện Tuyết Sương, Hải Phòng",
                    "thac.thoi@yahoo.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Bàng Trí",
                    true,
                    2001,
                    "961 Phố Cam Hòa Thiên, Xã Điền, Huyện Phan Thục Võ, Hồ Chí Minh",
                    "vong09@cao.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Viên Hòa",
                    true,
                    2001,
                    "3216 Phố Chung Cương Đăng, Xã Chương, Quận Mạnh, Kon Tum",
                    "che.long@dong.info.vn"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Hình Thêu",
                    true,
                    2001,
                    "94 Phố Tào Viên Phương, Xã 2, Quận Ái Tôn, Đà Nẵng",
                    "trinh.ha@ha.org.vn"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Ngân Diệu Thảo",
                     false,
                    2001,
                    "59 Phố Cầm, Phường 0, Quận Trưởng Kha, Đồng Nai",
                    "ngan.bao@huynh.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Nghị Tụ",
                    false,
                    2001,
                    "21 Phố Mạc Yên Điệp, Phường Đàm Giao Thể, Quận Giáp Vinh, Bạc Liêu",
                    "phi.luu@hotmail.com"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Biện Vy Ái",
                    false,
                    2001,
                    "09 Phố Đôn My Tụ, Phường 9, Huyện Hảo Sang, Hải Dương",
                    "bbanh@vuong.info.vn"
            )),
            StudentAccountCreateAndProfile.of(RandomInfo.getProfileUpdate(
                    "Giao Băng",
                    true,
                    2001,
                    "62 Phố Tòng Quỳnh Hằng, Xã Tuệ Nhu, Huyện 30, Ninh Bình",
                    "nghi.lai@yahoo.com"
            ))
    );
}
