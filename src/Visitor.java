package src;

/**
 * Created by octavian.guzu on 1/5/2017.
 */
public interface Visitor {
    //public void visit(Department department);
    public void visit(BookDepartment bookDepartment) throws CloneNotSupportedException;

    public void visit(MusicDepartment musicDepartment);

    public void visit(SoftwareDepartment softwareDepartment) throws CloneNotSupportedException;

    public void visit(VideoDepartment videoDepartment) throws CloneNotSupportedException;
}
