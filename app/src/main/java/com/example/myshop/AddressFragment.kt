// AddressFragment.kt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.myshop.R
import com.example.myshop.databinding.FragmentAddressBinding // Ganti dengan package Anda

class AddressFragment : Fragment() {
    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengambil array provinsi dari strings.xml
        val provinces = resources.getStringArray(R.array.provinces)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, provinces)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerProvinces.adapter = adapter

        binding.btnDone.setOnClickListener {
            // Mengirim data terpilih ke fragment sebelumnya (CheckoutFragment)
            findNavController().previousBackStackEntry?.savedStateHandle?.set("address", binding.spinnerProvinces.selectedItem.toString())
            // Kembali ke fragment sebelumnya
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}