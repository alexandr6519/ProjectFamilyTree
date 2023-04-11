import java.util.Iterator;
import java.util.List;

public class FamilyTreeIterator<H> implements Iterator<H> {
    private int index;
    private List<H> humanList;

    public FamilyTreeIterator(List<H> humanList) {
        this.humanList = humanList;
    }

    @Override
    public boolean hasNext() {
        return index < humanList.size();
    }

    @Override
    public H next() {
        return humanList.get(index++);
    }
}
