package net.corda.contracts.isolated

import net.corda.core.contracts.*
import net.corda.core.crypto.CompositeKey
import net.corda.core.crypto.Party
import net.corda.core.crypto.SecureHash
import net.corda.core.transactions.TransactionBuilder

// The dummy contract doesn't do anything useful. It exists for testing purposes.

val ANOTHER_DUMMY_PROGRAM_ID = AnotherDummyContract()

class AnotherDummyContract : Contract, net.corda.core.node.DummyContractBackdoor {
    data class State(val magicNumber: Int = 0) : ContractState {
        override val contract = ANOTHER_DUMMY_PROGRAM_ID
        override val participants: List<CompositeKey>
            get() = emptyList()
    }

    interface Commands : CommandData {
        class Create : TypeOnlyCommandData(), TransactionBuildable
    }

    override fun verify(tx: TransactionForContract) {
        // Always accepts.
    }

    // The "empty contract"
    override val legalContractReference: SecureHash = SecureHash.sha256("https://anotherdummy.org")

    override fun generateInitial(owner: PartyAndReference, magicNumber: Int, notary: Party): TransactionBuilder {

        val state = State(magicNumber)
        val cmd: TransactionBuildable = Command(Commands.Create(), owner.party.owningKey)
        return TransactionType.General.Builder(notary = notary).withItems(state, cmd)
    }

    override fun inspectState(state: ContractState): Int = (state as State).magicNumber

}
