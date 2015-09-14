package presentation._08_fake_not_meeting_the_contract_of_the_interface;

public interface UuidProvider {

    /**
     * Returns new universally unique identifier. It is guaranteed that returned value
     * is different than last 1000 previously returned values.
     */
    String nextUuid();

}
