import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myshop.databinding.FragmentCheckoutBinding
import com.example.myshop.CheckoutFragmentArgs
import com.example.myshop.CheckoutFragmentDirections

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!

    private val args: CheckoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Menampilkan nama produk yang diterima dari HomeFragment
        binding.txtProductName.text = args.productName

        // 2. Menangani klik pada EditText alamat untuk pindah ke AddressFragment
        binding.edtAddress.setOnClickListener {
            val action = CheckoutFragmentDirections.actionCheckoutFragmentToAddressFragment()
            findNavController().navigate(action)
        }

        // 3. Menerima data alamat yang dikirim kembali dari AddressFragment
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("address")
            ?.observe(viewLifecycleOwner) { address ->
                binding.edtAddress.setText(address)
            }

        // 4. Menangani klik pada tombol "Selesai" untuk kembali ke fragment sebelumnya (HomeFragment)
        binding.btnDone.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}