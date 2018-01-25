package be.simonraes.trainhome.rx

import io.reactivex.schedulers.Schedulers

/**
 * Test schedulers to facilitate testing.
 */

class TestSchedulerProvider : SchedulerProvider {
    override fun ui() = Schedulers.trampoline()

    override fun computation() = Schedulers.trampoline()

    override fun trampoline() = Schedulers.trampoline()

    override fun newThread() = Schedulers.trampoline()

    override fun io() = Schedulers.trampoline()
}
