package src;

/**
 * Created by octavian.guzu on 1/5/2017.
 */
public interface Visitor {
    //public void visit(Department department);
    public void visit(BookDepartment bookDepartment);
    public void visit(MusicDepartment musicDepartment);
    public void visit(SoftwareDepartment softwareDepartment);
    public void visit(VideoDepartment videoDepartment);
}
