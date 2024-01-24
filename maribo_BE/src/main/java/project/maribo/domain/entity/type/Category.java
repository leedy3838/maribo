package project.maribo.domain.entity.type;

public enum Category {
    INFORMATION, QUESTION, ADVERTISE, NORMAL;

    public static Category of (String category) {
        if (category.equalsIgnoreCase("information")) return Category.INFORMATION;
        else if (category.equalsIgnoreCase("question")) return Category.QUESTION;
        else if (category.equalsIgnoreCase("advertise")) return Category.ADVERTISE;
        else if (category.equalsIgnoreCase("normal")) return Category.NORMAL;
        else return Category.NORMAL;
    }
}
