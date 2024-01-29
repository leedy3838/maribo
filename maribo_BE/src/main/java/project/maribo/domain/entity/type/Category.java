package project.maribo.domain.entity.type;

public enum Category {
    INFORMATION, QUESTION, ADVERTISE, NORMAL;

    public static Category of(String category) {
        if (category == null) {
            return Category.NORMAL; // 입력값이 null일 경우 기본값으로 NORMAL을 반환
        }

        if (category.equalsIgnoreCase("information")) return Category.INFORMATION;
        else if (category.equalsIgnoreCase("question")) return Category.QUESTION;
        else if (category.equalsIgnoreCase("advertise")) return Category.ADVERTISE;
        else if (category.equalsIgnoreCase("normal")) return Category.NORMAL;
        else return Category.NORMAL;
    }
}
